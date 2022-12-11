package project.st991575494.navjotandranvir

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.ViewModels.UserConfirmBookingViewModel
import project.st991575494.navjotandranvir.databinding.FragmentUserConfirmBookingBinding

// This class is reponsible to confirm the user booking

class UserConfirmBookingFragment : Fragment() {

    companion object {
        fun newInstance() = UserConfirmBookingFragment()
    }

    private lateinit var viewModel: UserConfirmBookingViewModel
    private var _binding: FragmentUserConfirmBookingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserConfirmBookingBinding.inflate(inflater, container, false)

        val bundle = arguments
        val serviceObj: Service? = bundle!!.getSerializable("service") as Service?
        serviceObj!!.additionalNotes = binding.editTextAdditionalNotes.text.toString();
        serviceObj!!.uid = FirebaseAuth.getInstance().uid

        binding.textViewConfirmVehicleType.setText(String.format("%5s %15s","Vehicle Type: ",serviceObj!!.vehicle))
        binding.textViewConfirmServiceType.setText(String.format("%5s %15s","Service Type: ",serviceObj!!.serviceType))
        binding.textViewServiceCharges.setText(String.format("%5s %15s","Subtotal: ",serviceObj!!.subtotal))
        binding.textViewServiceTax.setText(String.format("%5s %15s","Tax(13%): ",serviceObj!!.tax))

        binding.buttonConfirmBooking.setOnClickListener {

            FirebaseFirestore.getInstance().collection("service_requests")
                .add(serviceObj)
                .addOnSuccessListener {

                   Toast.makeText(context,"Booking Created Successfully", Toast.LENGTH_SHORT).show()
                    val userHomeFragment = UserHomeFragment()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, userHomeFragment)?.commit()
                }
                .addOnFailureListener({
                    Log.d(TAG, "not worked")
                })



        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserConfirmBookingViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}