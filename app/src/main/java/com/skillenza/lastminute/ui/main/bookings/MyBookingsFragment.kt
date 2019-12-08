package com.skillenza.lastminute.ui.main.bookings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.skillenza.lastminute.R
import com.skillenza.lastminute.ui.base.BaseFragment
import com.skillenza.lastminute.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_bookings.*

class MyBookingsFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!).get(MainViewModel::class.java) }

    override fun getLayoutResourceId() = R.layout.fragment_my_bookings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachViewModel()

        if (mainViewModel.myBookings.isEmpty()) {
            baseActivity.showProgressDialog("Loading your bookings")
            mainViewModel.getMyBookings()
        } else {
            showList()
        }

        srlMyBookings.setOnRefreshListener {
            baseActivity.showProgressDialog("Loading your bookings")
            mainViewModel.getMyBookings()
        }
    }

    private fun attachViewModel() {
        mainViewModel.successMyBookings.observe(this, Observer { successMyBookings ->
            if (successMyBookings != null) {
                baseActivity.hideProgressDialog()
                srlMyBookings.isRefreshing = false
                if (successMyBookings) {
                    showList()
                } else {
                    baseActivity.showSnackBar(mainViewModel.message, Snackbar.LENGTH_LONG)
                }
            }
        })
    }

    private fun showList() {
        if (mainViewModel.myBookings.isNotEmpty()) {
            rvMyBookings.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = BookingsAdapter(mainViewModel.myBookings)
            }
            srlMyBookings.visibility = View.VISIBLE
            tvNoBookings.visibility = View.GONE
        } else {
            srlMyBookings.visibility = View.GONE
            tvNoBookings.visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MyBookingsFragment()
    }
}
