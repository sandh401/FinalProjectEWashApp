package project.st991575494.navjotandranvir.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


// This class is reponsible to populated pending bookings recyclerview
class AdminBookingAdapter (private val bookingList : ArrayList<Service>) : RecyclerView.Adapter<AdminBookingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.admin_booking, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = bookingList[position]
        //set the values in the views
        holder.vehicle.text = String.format("%s %5s","Vehicle: ",currentItem.vehicle)
        holder.total.text = String.format("%s %5s","Total: ",currentItem.total.toString())
        holder.service.text = String.format("%s %5s","Service: ",currentItem.serviceType)
        holder.status.text = String.format("%s %5s","Status: ",currentItem.status)
        holder.date.text = String.format("%s %5s","Date: ",
            SimpleDateFormat("yyyy-MM-dd").format(Date(currentItem.date.toLong())));


        // For approving requests
        holder.btnAccept.setOnClickListener {
            var pos = holder.adapterPosition
            val newStatus = "approved"
            currentItem.status = newStatus
            bookingList[pos] = currentItem
            FirebaseFirestore.getInstance().collection("service_requests").document(currentItem.sid).update("status", newStatus)
            bookingList.remove(currentItem)
            val snack = Snackbar.make(it,"Request Approved", Snackbar.LENGTH_LONG)
            snack.show()
            notifyItemRemoved(pos)
        }

        //for declining requests
        holder.btnDecline.setOnClickListener {
            var pos = holder.adapterPosition
            val newStatus = "declined"
            var booking = currentItem
            currentItem.status = newStatus
            bookingList[pos] = currentItem
            FirebaseFirestore.getInstance().collection("service_requests").document(currentItem.sid).update("status", newStatus)
            bookingList.remove(currentItem)
            val snack = Snackbar.make(it,"Request Declined", Snackbar.LENGTH_LONG)
            snack.show()
            notifyItemRemoved(pos)
        }


    }



    override fun getItemCount(): Int {
        return bookingList.size
    }

    public class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        // create holder views
        val total : TextView = itemView.findViewById(R.id.txtViewTotal)
        val status : TextView = itemView.findViewById(R.id.txtViewStatus)
        val vehicle : TextView = itemView.findViewById(R.id.txtViewCar)
        val date : TextView = itemView.findViewById(R.id.txtViewRequestDate)
        val service : TextView = itemView.findViewById(R.id.txtViewService)

        val btnAccept : Button = itemView.findViewById(R.id.btnApprove)
        val btnDecline : Button = itemView.findViewById(R.id.btnDecline)

    }
}