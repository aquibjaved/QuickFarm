package com.skillenza.lastminute.ui.main

import androidx.lifecycle.MutableLiveData
import com.skillenza.lastminute.models.Booking
import com.skillenza.lastminute.models.Listing
import com.skillenza.lastminute.ui.base.BaseViewModel
import com.skillenza.lastminute.utils.CustomRetrofitCallBack
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import java.util.*

class MainViewModel: BaseViewModel() {

    val successMyBookings: MutableLiveData<Boolean> = MutableLiveData()
    val successMyListings: MutableLiveData<Boolean> = MutableLiveData()
    val successAllListings: MutableLiveData<Boolean> = MutableLiveData()
    val successCreateListings: MutableLiveData<Boolean> = MutableLiveData()
    val successNewBooking: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var myBookingsCall: Call<List<Booking>>
    private lateinit var myListingCall: Call<List<Listing>>
    private lateinit var allListingsCall: Call<List<Listing>>
    private lateinit var createListingCall: Call<ResponseBody>
    private lateinit var newBookingCall: Call<ResponseBody>

    val myBookings = mutableListOf<Booking>()
    val myListings = mutableListOf<Listing>()
    val allListings = mutableListOf<Listing>()

    fun getMyBookings() {
        myBookingsCall = dataManager.getMyBookings()
        addNetworkCall(myBookingsCall)
        myBookingsCall.enqueue(object: CustomRetrofitCallBack<List<Booking>> {
            override val TAG = this@MainViewModel.TAG
            override fun onSuccess(response: List<Booking>) {
                myBookings.clear()
                response.forEach {
                    myBookings.add(it)
                }
                successMyBookings.value = true
            }
            override fun onError(errorMessage: String) {
                message = errorMessage
                successMyBookings.value = false
            }
        })
    }

    fun getMyListings() {
        myListingCall = dataManager.getMyListings()
        addNetworkCall(myListingCall)
        myListingCall.enqueue(object: CustomRetrofitCallBack<List<Listing>> {
            override val TAG = this@MainViewModel.TAG
            override fun onSuccess(response: List<Listing>) {
                myListings.clear()
                response.forEach {
                    myListings.add(it)
                }
                successMyListings.value = true
            }
            override fun onError(errorMessage: String) {
                message = errorMessage
                successMyListings.value = false
            }
        })
    }

    fun getAllListings() {
        allListingsCall = dataManager.getAllListings()
        addNetworkCall(allListingsCall)
        allListingsCall.enqueue(object: CustomRetrofitCallBack<List<Listing>> {
            override val TAG = this@MainViewModel.TAG
            override fun onSuccess(response: List<Listing>) {
                allListings.clear()
                response.forEach {
                    allListings.add(it)
                }
                successAllListings.value = true
            }
            override fun onError(errorMessage: String) {
                message = errorMessage
                successAllListings.value = false
            }
        })
    }

    fun createListing(name: String, type: String, price: String) {
        createListingCall = dataManager.newListing(name, type, price)
        addNetworkCall(createListingCall)
        createListingCall.enqueue(object: CustomRetrofitCallBack<ResponseBody> {
            override val TAG = this@MainViewModel.TAG
            override fun onSuccess(response: ResponseBody) {
                successCreateListings.value = true
            }
            override fun onError(errorMessage: String) {
                message = errorMessage
                successCreateListings.value = false
            }
        })
    }

    fun newBooking(machineId: String, machineName: String, bookingDate: String) {
        newBookingCall = dataManager.doNewBooking(machineId, machineName, bookingDate)
        addNetworkCall(newBookingCall)
        newBookingCall.enqueue(object: CustomRetrofitCallBack<ResponseBody> {
            override val TAG = this@MainViewModel.TAG
            override fun onSuccess(response: ResponseBody) {
                successNewBooking.value = true
            }
            override fun onError(errorMessage: String) {
                message = "Machine maybe already booked"
                successNewBooking.value = false
            }
        })
    }
}
