package com.skillenza.lastminute.ui.main.bookings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.skillenza.lastminute.R
import com.skillenza.lastminute.models.Booking
import com.skillenza.lastminute.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_my_booking.view.*

class BookingsAdapter(private val bookings: List<Booking>): BaseAdapter<Booking, BookingsAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_booking, parent, false))
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.tvMachineName.text = bookings[position].machineName
        holder.tvDate.text = bookings[position].date.toString()
    }

    override fun getItemCount() = bookings.count()

    class BookingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvMachineName: AppCompatTextView = itemView.tvBookingMachineName
        val tvDate: AppCompatTextView = itemView.tvDate
    }
}
