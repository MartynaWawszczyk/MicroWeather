package com.tysia.microweather.data.model

import java.io.Serializable

class CurrentVsPredictedResponse : Serializable {

    var cityDto : CityData? = null
    var date : String? = null

    var currentTemp : Double? = null
    var currentFeelsLike : Double? = null
    var currentWindSpeed : Double? = null
    var currentPressure : Double? = null

    var predictedTemp : Double? = null
    //var predictedNightTemp : Double? = null
    var predictedFeelsLike : Double? = null
    //var predictedFeelsLikeNight : Double? = null
    var predictedWindSpeed : Double? = null
    var predictedPressure : Double? = null
}