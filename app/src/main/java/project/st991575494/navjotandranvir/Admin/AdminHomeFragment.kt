package project.st991575494.navjotandranvir.Admin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.UserViewBookingFragment
import project.st991575494.navjotandranvir.ViewModels.AdminHomeViewModel
import project.st991575494.navjotandranvir.databinding.FragmentAdminHomeBinding

// Home Page of Admin

class AdminHomeFragment : Fragment() {


    companion object {
        fun newInstance() = AdminHomeFragment()
    }

    private lateinit var viewModel: AdminHomeViewModel
    private lateinit var binding: FragmentAdminHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)

        // Go View all bookings page to approve and decline
        binding.btnViewBooking.setOnClickListener {

            val fragmentViewBooking = AdminViewBookingFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, fragmentViewBooking)?.commit()
        }


        // Go View all accepted bookings page

        binding.btnViewAcceptedBooking.setOnClickListener {
            val fragmentViewBooking = AdminViewAcceptedBookingsFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, fragmentViewBooking)?.commit()
        }


        // Go View all rejected bookings page
        binding.btnViewRejectedBooking.setOnClickListener {

            val fragmentViewBooking = AdminViewDeclinedBookingsFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, fragmentViewBooking)?.commit()

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminHomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}