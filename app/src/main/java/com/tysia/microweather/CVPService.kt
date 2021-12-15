package com.tysia.microweather

import com.tysia.microweather.data.model.ClothingResponse
import com.tysia.microweather.data.model.CurrentVsPredictedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CVPService {
    @GET("/api/analysis/{cityId}")
    fun getCVP(@Path("cityId") city : String): Call<CurrentVsPredictedResponse>
}