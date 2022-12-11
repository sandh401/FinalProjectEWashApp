package project.st991575494.navjotandranvir.Auth


import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import project.st991575494.navjotandranvir.Admin.AdminHomeFragment
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.UserHomeFragment
import project.st991575494.navjotandranvir.ViewModels.UserViewModel
import project.st991575494.navjotandranvir.databinding.FragmentLoginBinding
import java.lang.Exception

// This class is reponsible to show the fragment for Login B
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var sharedViewModel: UserViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        sharedViewModel.email.observe(viewLifecycleOwner, { e ->
            binding.editTextEmail.setText(e)
        })

        sharedViewModel.password.observe(viewLifecycleOwner, { password ->
            binding.editTextPassword.setText(password)
        })



        // Get the UI state and its values
        binding.editTextEmail.setText(savedInstanceState?.getString("email"))
        binding.editTextPassword.setText(savedInstanceState?.getString("pass"))


        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        // Login button
        binding.btnLogin?.setOnClickListener {

            //Check if fiels are empty
            if(binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()) {
                val snack = Snackbar.make(it,"Both Email And Password Are Required",Snackbar.LENGTH_LONG)
                snack.show()
                return@setOnClickListener
            }

            sharedViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
            FirebaseApp.initializeApp(requireContext());
            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {


                        Log.d(TAG,"User logged in Successfully with Uid " + task.result.user?.uid)

                        // Check if admin
                        if(binding.editTextEmail.text.toString().equals("admin@ewash.com")){
                            val fragmentHome = AdminHomeFragment()
                            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragmentHome)?.commit()

                            // Save state for login
                            val sharedPreference =  requireActivity().getSharedPreferences("APP_STATE", Context.MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("login_state","admin")
                            editor.commit()
                        }
                        else{
                            val fragmentHome = UserHomeFragment()
                            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragmentHome)?.commit()
                            // Save state for login
                            val sharedPreference =  requireActivity().getSharedPreferences("APP_STATE", Context.MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("login_state","user")
                            editor.commit()
                        }


                    } else {
                        Log.d(TAG,"An error occured while logging in")
                        val snack = Snackbar.make(it,"User not found",Snackbar.LENGTH_LONG)
                        snack.show()
                    }
                }
                .addOnFailureListener { task ->

                    Log.d(TAG,"An error occured while logging in")
                }


        }


        // Register user buttib


        binding.btnSignUp.setOnClickListener {
            val registerFragment = RegisterFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, registerFragment)?.commit()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }


    // Save the UI state and its values

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try{
            outState.putString("email", binding.editTextEmail.text.toString() )
            outState.putString("pass", binding.editTextPassword.text.toString()  )
        }
        catch (ex: Exception){

        }


    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }




}