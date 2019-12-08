package com.skillenza.lastminute.remote

import com.skillenza.lastminute.models.Booking
import com.skillenza.lastminute.models.Listing
import com.skillenza.lastminute.remote.requests.BookingRequest
import com.skillenza.lastminute.remote.requests.CreateListingRequest
import com.skillenza.lastminute.remote.requests.LoginRequest
import com.skillenza.lastminute.remote.responses.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @POST("user/register")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("booking/user/{userId}")
    fun myBookings(@Path("userId") userId: String): Call<List<Booking>>

    @GET("machine/user/{userId}")
    fun myListings(@Path("userId") userId: String): Call<List<Listing>>

    @GET("machine/all")
    fun allListings(): Call<List<Listing>>

    @POST("booking/register")
    fun doNewBooking(@Body bookingRequest: BookingRequest): Call<ResponseBody>

    @POST("machine/register")
    fun createListing(@Body createListingRequest: CreateListingRequest): Call<ResponseBody>

}
