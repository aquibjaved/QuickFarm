package com.skillenza.lastminute.remote

import com.skillenza.lastminute.models.Booking
import com.skillenza.lastminute.models.Listing
import com.skillenza.lastminute.remote.requests.BookingRequest
import com.skillenza.lastminute.remote.requests.LoginRequest
import com.skillenza.lastminute.remote.responses.LoginResponse
import com.skillenza.lastminute.utils.PreferenceManager
import okhttp3.ResponseBody
import retrofit2.Call
import java.util.*
import com.skillenza.lastminute.remote.requests.CreateListingRequest as CreateListingRequest

class DataManager {

    private val service = ApiManager.instance.service

    fun login(phoneNumber: String): Call<LoginResponse> {
        return service.login(LoginRequest(phoneNumber))
    }

    fun getMyBookings(): Call<List<Booking>> {
        return service.myBookings(PreferenceManager.userId)
    }

    fun getMyListings(): Call<List<Listing>> {
        return service.myListings(PreferenceManager.userId)
    }

    fun getAllListings(): Call<List<Listing>> {
        return service.allListings()
    }

    fun doNewBooking(machineId: String, machineName: String, bookingDate: String): Call<ResponseBody> {
        val bookingRequest = BookingRequest(PreferenceManager.userId, machineId, machineName, bookingDate)
        return service.doNewBooking(bookingRequest)
    }

    fun newListing(name: String, type: String, price: String): Call<ResponseBody> {
        val createListingRequest = CreateListingRequest(name, PreferenceManager.userId, type, price)
        return service.createListing(createListingRequest)
    }
}
