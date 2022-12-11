package project.st991575494.navjotandranvir.Auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.ViewModels.UserViewModel
import project.st991575494.navjotandranvir.databinding.FragmentRegisterBinding

// This class is reponsible to show the fragment for Registration

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: UserViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        //viewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        sharedViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

// Get the UI state and its values
        binding.editTextEmail.setText(savedInstanceState?.getString("email"))
        binding.editTextPassword.setText(savedInstanceState?.getString("pass"))

        binding.btnRegister.setOnClickListener {

            //check if any field is empty
            if(binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()){
                val snack = Snackbar.make(it,"Both Email And Password Are Required", Snackbar.LENGTH_LONG)
                snack.show()
                return@setOnClickListener
            }

            sharedViewModel.saveEmail(binding.editTextEmail.text.toString())
            sharedViewModel.savePassword(binding.editTextPassword.text.toString())


            // Initialize Firebase Auth
            FirebaseApp.initializeApp(requireContext());
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Save user and its user if for later use
                        task.result.user?.let { it1 -> sharedViewModel.saveUID(it1.uid) }

                        val snack = Snackbar.make(it,"User Registered Successfully ",Snackbar.LENGTH_LONG)
                        snack.show()
                        val loginFragment = LoginFragment()
                        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, loginFragment)?.commit()

                    } else {
                        val snack = Snackbar.make(it,"An error was encountered while creating user ",Snackbar.LENGTH_LONG)
                        snack.show()
                    }
                }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", binding.editTextEmail.text.toString() )
        outState.putString("pass", binding.editTextPassword.text.toString()  )
    }

}