package project.st991575494.navjotandranvir.Admin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import project.st991575494.navjotandranvir.Adapter.AdminBookingAdapter
import project.st991575494.navjotandranvir.Adapter.UserBookingAdapter
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.R
import project.st991575494.navjotandranvir.ViewModels.AdminViewBookingViewModel
import project.st991575494.navjotandranvir.ViewModels.UserViewBookingViewModel

class AdminViewBookingFragment : Fragment() {



    companion object {
        fun newInstance() = AdminViewBookingFragment()
    }

    private lateinit var viewModel: AdminViewBookingViewModel
    private lateinit var bookingList: ArrayList<Service>
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtNoData: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_view_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view?.findViewById(R.id.bookingList)!!
        txtNoData = view?.findViewById(R.id.txtNoDataFound)!!
        recyclerView.layoutManager = layoutManager

        bookingList = arrayListOf()

        FirebaseFirestore.getInstance().collection("service_requests").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for (data in it.documents){
                        val booking: Service? = data.toObject(Service::class.java)
                        if(booking != null){
                            bookingList.add(booking)
                        }
                    }

                    if(bookingList.size > 0){
                        recyclerView.adapter = AdminBookingAdapter(bookingList)
                    }
                    else{
                        txtNoData.text = "No Booking Requests Found";
                    }

                }
            }
            .addOnFailureListener{
                Toast.makeText(context, "An error occcure while reading data", Toast.LENGTH_SHORT)
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminViewBookingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}