package project.st991575494.navjotandranvir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import project.st991575494.navjotandranvir.Auth.LoginFragment
import project.st991575494.navjotandranvir.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val fragmentLogin = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentLogin).commit()

        }

    }



}