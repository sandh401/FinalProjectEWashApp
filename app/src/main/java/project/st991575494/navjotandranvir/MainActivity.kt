package project.st991575494.navjotandranvir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import com.google.android.gms.common.api.internal.GoogleServices.initialize
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    if(savedInstanceState == null){
        val fragmentLogin = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentLogin).commit()
        }

    }


}