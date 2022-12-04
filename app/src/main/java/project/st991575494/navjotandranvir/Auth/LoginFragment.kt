package project.st991575494.navjotandranvir.Auth

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import project.st991575494.navjotandranvir.Admin.AdminHomeFragment
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.UserHomeFragment
import project.st991575494.navjotandranvir.ViewModels.UserViewModel
import project.st991575494.navjotandranvir.databinding.FragmentLoginBinding

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



        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnLogin?.setOnClickListener {
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
                        if(binding.editTextEmail.text.toString().equals("admin@ewash.com")){
                            val fragmentHome = AdminHomeFragment()
                            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragmentHome)?.commit()
                        }
                        else{
                            val fragmentHome = UserHomeFragment()
                            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragmentHome)?.commit()

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


//            val userSelectFragment = UserSelectServiceFragment()
//            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userSelectFragment)?.commit()
        }



        binding.btnSignUp.setOnClickListener {
            val registerFragment = RegisterFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, registerFragment)?.commit()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}