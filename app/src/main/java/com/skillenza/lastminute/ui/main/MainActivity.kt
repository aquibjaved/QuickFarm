package com.skillenza.lastminute.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.skillenza.lastminute.R
import com.skillenza.lastminute.ui.base.BaseActivity
import com.skillenza.lastminute.ui.main.alllistings.AllListingsFragment
import com.skillenza.lastminute.ui.main.bookings.MyBookingsFragment
import com.skillenza.lastminute.ui.main.mylistings.MyListingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    private val mainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    private var atHome = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationSetup()
        replaceFragment(R.id.frameLayout,
            MyBookingsFragment(), R.string.my_bookings_fragment)
    }

    private fun bottomNavigationSetup() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_my_bookings -> {
                    replaceFragment(R.id.frameLayout,
                        MyBookingsFragment.newInstance(), R.string.my_bookings_fragment)
                    atHome = true
                    true
                }
                R.id.nav_my_listings -> {
                    replaceFragment(R.id.frameLayout,
                        MyListingsFragment.newInstance(), R.string.my_listings_fragment)
                    atHome = false
                    true
                }
                R.id.nav_all_listings -> {
                    replaceFragment(R.id.frameLayout,
                        AllListingsFragment.newInstance(), R.string.all_listings_fragment)
                    atHome = false
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (!atHome) {
            replaceFragment(R.id.frameLayout,
                MyBookingsFragment(), R.string.my_bookings_fragment)
            atHome = true
            bottomNavigationView.selectedItemId = R.id.nav_my_bookings
        } else {
            super.onBackPressed()
        }
    }
}
