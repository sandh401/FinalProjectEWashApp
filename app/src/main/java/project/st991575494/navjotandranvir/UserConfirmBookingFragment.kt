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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.databinding.FragmentUserConfirmBookingBinding
import project.st991575494.navjotandranvir.databinding.FragmentUserSelectBinding

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

        binding.textViewConfirmVehicleType.setText(String.format("%15s %15s","Vehicle Type: ",serviceObj!!.vehicle))
        binding.textViewConfirmVehicleType.setText(String.format("%15s %15s","Service Type: ",serviceObj!!.serviceType))
        binding.textViewConfirmVehicleType.setText(String.format("%15s %15s","Subtotal: ",serviceObj!!.subtotal))
        binding.textViewConfirmVehicleType.setText(String.format("%15s %15s","Tax(13%): ",serviceObj!!.tax))

        binding.buttonConfirmBooking.setOnClickListener {

            FirebaseFirestore.getInstance().collection("service_requests")
                .add(serviceObj)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Booking confirmed, thank you", Toast.LENGTH_SHORT)
                    val selectServiceFragment = UserSelectServiceFragment()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, selectServiceFragment)?.commit()
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