package com.skillenza.lastminute.ui.main.mylistings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.skillenza.lastminute.R
import com.skillenza.lastminute.models.Listing
import com.skillenza.lastminute.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_my_listing.view.*

class MyListingsAdapter(private val myListings: List<Listing>): BaseAdapter<Listing, MyListingsAdapter.MyListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListingViewHolder {
        return MyListingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_listing, parent, false))
    }

    override fun getItemCount() = myListings.count()

    override fun onBindViewHolder(holder: MyListingsAdapter.MyListingViewHolder, position: Int) {
        holder.tvName.text = myListings[position].machineName
        holder.tvType.text = myListings[position].machineType
        holder.tvPrice.text = myListings[position].mPrice
    }

    class MyListingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: AppCompatTextView = itemView.tvName
        val tvType: AppCompatTextView = itemView.tvType
        val tvPrice: AppCompatTextView = itemView.tvPrice
    }
}