package project.st991575494.navjotandranvir.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.st991575494.navjotandranvir.Data.Service
import project.st991575494.navjotandranvir.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


// This class is reponsible to populated user  bookings recyclerview
class UserBookingAdapter (private val bookingList : ArrayList<Service>) : RecyclerView.Adapter<UserBookingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.booking, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookingList[position]
        //set the values in the views
        holder.vehicle.text = String.format("%s %5s","Vehicle: ",currentItem.vehicle)
        holder.total.text = String.format("%s %5s","Total: ",currentItem.total.toString())
        holder.service.text = String.format("%s %5s","Service: ",currentItem.serviceType)
        holder.status.text = String.format("%s %5s","Status: ",currentItem.status)
        holder.date.text = String.format("%s %5s","Date: ",SimpleDateFormat("yyyy-MM-dd").format(Date(currentItem.date.toLong())));


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

    }
}