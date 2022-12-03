package project.st991575494.navjotandranvir.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private var _email = MutableLiveData<String>()
    val email : LiveData<String>
    get() = _email

    private var _password = MutableLiveData<String>()
    val password : LiveData<String>
    get() = _password

    fun saveEmail(newEmail: String){

        _email.value = newEmail
    }

    fun savePassword(newPassword: String){

        _password.value = newPassword
    }



}