package project.st991575494.navjotandranvir

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.st991575494.navjotandranvir.ViewModels.UserHomeViewModel
import project.st991575494.navjotandranvir.databinding.FragmentUserHomeBinding
// This class is reponsible to show the home page of user
class UserHomeFragment : Fragment() {

    companion object {
        fun newInstance() = UserHomeFragment()
    }

    private lateinit var viewModel: UserHomeViewModel
    private lateinit var binding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserHomeBinding.inflate(inflater, container, false)

        binding.btnCreateBooking.setOnClickListener {
            val fragmentCreateBooking = UserSelectServiceFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, fragmentCreateBooking)?.commit()

        }

        binding.btnViewBooking.setOnClickListener {

            val fragmentViewBooking = UserViewBookingFragment()
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container, fragmentViewBooking)?.commit()
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserHomeViewModel::class.java)
        // TODO: Use the ViewModel

    }

}