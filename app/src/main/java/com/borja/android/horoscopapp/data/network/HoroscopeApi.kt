package com.borja.android.horoscopapp.data.network

import com.borja.android.horoscopapp.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HoroscopeApi {
    //@POST(".")// el . consume la url directa

    @GET("/{sign}/")
     suspend fun getHoroscope(
        @Path("sign") sign:String,
        @Query("date") date:String,
        @Query("lang") lang:String
    ): Response<HoroscopeResponse>
}