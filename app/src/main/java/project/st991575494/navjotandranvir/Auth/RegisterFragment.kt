package project.st991575494.navjotandranvir.Auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.ViewModels.UserViewModel
import project.st991575494.navjotandranvir.databinding.FragmentRegisterBinding

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


        binding.btnRegister.setOnClickListener {

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

                        task.result.user?.let { it1 -> sharedViewModel.saveUID(it1.uid) }

                        val snack = Snackbar.make(it,"User Registered Successfully ",Snackbar.LENGTH_LONG)
                        snack.show()
                        val loginFragment = LoginFragment()
                        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, loginFragment)?.commit()

                    //                        val userSelectFragment = UserSelectServiceFragment()
//                        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userSelectFragment)?.commit()


                    } else {
                        val snack = Snackbar.make(it,"An error was encountered while creating user ",Snackbar.LENGTH_LONG)
                        snack.show()
                    }
                }
        }
        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}