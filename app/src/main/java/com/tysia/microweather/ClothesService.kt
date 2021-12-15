package com.tysia.microweather

import com.tysia.microweather.data.model.ClothingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClothesService {
    @GET("/api/advice/{cityId}")
    fun getClothesResponse(@Path("cityId") name : String): Call<ClothingResponse>
}