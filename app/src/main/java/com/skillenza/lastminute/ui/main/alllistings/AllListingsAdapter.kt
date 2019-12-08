package com.skillenza.lastminute.ui.main.alllistings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.skillenza.lastminute.R
import com.skillenza.lastminute.models.Listing
import com.skillenza.lastminute.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_all_listing.view.*

class AllListingsAdapter(
    private val allListings: List<Listing>,
    private val bookListing: (String, String) -> Unit): BaseAdapter<Listing, AllListingsAdapter.AllListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllListingViewHolder {
        return AllListingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_listing, parent, false))
    }

    override fun getItemCount() = allListings.count()

    override fun onBindViewHolder(holder: AllListingViewHolder, position: Int) {
        holder.tvName.text = allListings[position].machineName
        holder.tvType.text = allListings[position].machineType
        holder.tvPrice.text = allListings[position].mPrice
        holder.cvListing.setOnClickListener {
            bookListing(allListings[position].id, allListings[position].machineName)
        }
    }

    class AllListingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvListing: CardView = itemView.cvListing
        val tvName: AppCompatTextView = itemView.tvName
        val tvType: AppCompatTextView = itemView.tvType
        val tvPrice: AppCompatTextView = itemView.tvPrice
    }
}
