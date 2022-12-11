package project.st991575494.navjotandranvir

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.rpc.Help
import project.st991575494.navjotandranvir.Admin.AdminHomeFragment
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

            val sharedPreference =  getSharedPreferences("APP_STATE", Context.MODE_PRIVATE)
            if(sharedPreference != null){
                val state = sharedPreference.getString("login_state", "" );
                if(state.equals("user", true)){
                    val fragmentHome = UserHomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentHome).commit()
                    return
                }
                if(state.equals("admin", true)){
                    val fragmentHome = AdminHomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentHome).commit()
                    return
                }
            }
            val fragmentLogin = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentLogin).commit()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId;
        if(id == R.id.menu_logout)
        {
            Firebase.auth.signOut();
            val sharedPreference =  getSharedPreferences("APP_STATE", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("login_state","")
            editor.commit()
            val fragmentLogin = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentLogin).commit()

        }
        if(id == R.id.menu_help)
        {
            val fragmentHelp = HelpFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentHelp).commit()
        }
        if(id == R.id.menu_about)
        {
            val fragmentAbout = AboutFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentAbout).commit()
        }

        return super.onOptionsItemSelected(item)
    }







}