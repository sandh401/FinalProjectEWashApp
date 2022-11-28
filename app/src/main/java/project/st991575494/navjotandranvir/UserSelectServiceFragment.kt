package project.st991575494.navjotandranvir

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class UserSelectServiceFragment : Fragment() {

    companion object {
        fun newInstance() = UserSelectServiceFragment()
    }

    private lateinit var viewModel: UserSelectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_select, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserSelectViewModel::class.java)
        // TODO: Use the ViewModel

        val buttonLogin = view?.findViewById<Button>(R.id.btnSelectService)

        buttonLogin?.setOnClickListener {
            val userConfirmBooking = UserConfirmBookingFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userConfirmBooking)?.commit()
        }
        //Text
    }

}