package project.st991575494.navjotandranvir

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import project.st991575494.navjotandranvir.ViewModels.LoginViewModel
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
            if(binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()) return@setOnClickListener

            sharedViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
            FirebaseApp.initializeApp(requireContext());
            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG,"User logged in Successfully with Uid " + task.result.user?.uid)
                        val userSelectFragment = UserSelectServiceFragment()
                        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userSelectFragment)?.commit()


                    } else {
                        Log.d(TAG,"An error occured while logging in")
                    }
                }


            val userSelectFragment = UserSelectServiceFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userSelectFragment)?.commit()
        }



        binding.btnSignUp.setOnClickListener {
            val registerFragment = RegisterFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, registerFragment)?.commit()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}