package com.skillenza.lastminute.ui.main.alllistings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.skillenza.lastminute.R
import com.skillenza.lastminute.ui.base.BaseFragment
import com.skillenza.lastminute.ui.main.MainViewModel
import com.skillenza.lastminute.utils.PreferenceManager
import kotlinx.android.synthetic.main.fragment_all_listings.*
import java.text.SimpleDateFormat
import java.util.*


class AllListingsFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!).get(MainViewModel::class.java) }

    override fun getLayoutResourceId() = R.layout.fragment_all_listings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachViewModel()

        if (mainViewModel.allListings.isEmpty()) {
            baseActivity.showProgressDialog("Fetching all listings")
            mainViewModel.getAllListings()
        } else {
            showList()
        }

        srlAllListings.setOnRefreshListener {
            baseActivity.showProgressDialog("Fetching all listings")
            mainViewModel.getAllListings()
        }
    }

    private fun attachViewModel() {
        mainViewModel.successAllListings.observe(this, Observer { successAllListings ->
            if (successAllListings != null) {
                baseActivity.hideProgressDialog()
                if (successAllListings) {
                    showList()
                } else {
                    baseActivity.showSnackBar(mainViewModel.message, Snackbar.LENGTH_LONG)
                }
            }
        })

        mainViewModel.successNewBooking.observe(this, Observer { successNewBooking ->
            if (successNewBooking != null) {
                baseActivity.hideProgressDialog()
                srlAllListings.isRefreshing = false
                if (successNewBooking) {
                    baseActivity.showToast("Booking confirmed", Toast.LENGTH_LONG)
                } else {
                    baseActivity.showSnackBar(mainViewModel.message, Snackbar.LENGTH_LONG)
                }
                mainViewModel.successNewBooking.value = null
            }

        })
    }

    private fun showList() {
        if (mainViewModel.allListings.isNotEmpty()) {
            rvAllListings.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = AllListingsAdapter(mainViewModel.allListings.filter { it.ownerId != PreferenceManager.userId }, bookListing)
            }
            srlAllListings.visibility = View.VISIBLE
            tvNoListings.visibility = View.GONE
        } else {
            srlAllListings.visibility = View.GONE
            tvNoListings.visibility = View.VISIBLE
        }
    }

    private val bookListing: (String, String) -> Unit = { machineId, machineName ->
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        baseActivity.showProgressDialog("Booking vehicle for you")
        mainViewModel.newBooking(machineId, machineName, date)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllListingsFragment()
    }
}
