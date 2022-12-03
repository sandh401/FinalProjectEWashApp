package project.st991575494.navjotandranvir.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private var _uid = MutableLiveData<String>()
    val uid: LiveData<String> = _uid


    private var _email = MutableLiveData<String>()
    val email : LiveData<String>
        get() = _email

    private var _password = MutableLiveData<String>()
    val password : LiveData<String>
        get() = _password


    fun saveUID(newUID: String){

        _uid.value = newUID
    }

    fun saveEmail(newEmail: String){

        _email.value = newEmail
    }

    fun savePassword(newPassword: String){

        _password.value = newPassword
    }
}