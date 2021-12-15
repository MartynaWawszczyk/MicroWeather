package com.tysia.microweather.data.model

import java.io.Serializable

class ClothingResponse() : Serializable {
    class Hours {
        var hoursAhead : String? = null
        var tips: List<String>? = null
    }

    var cityDto: CityData? = null
    var adviceList : List<Hours>? = null
}
