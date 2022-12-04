package project.st991575494.navjotandranvir.Admin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.ViewModels.AdminAcceptRequestViewModel

class AdminAcceptRequestFragment : Fragment() {

    companion object {
        fun newInstance() = AdminAcceptRequestFragment()
    }

    private lateinit var viewModel: AdminAcceptRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_accept_request, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminAcceptRequestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}