package com.tysia.microweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tysia.microweather.data.model.ClothingResponse
import com.tysia.microweather.data.model.CurrentVsPredictedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class CitiesViewModel : ViewModel() {
    private val _clothingResponse = MutableLiveData<ClothingResponse>()
    val clothingResponse : LiveData<ClothingResponse> = _clothingResponse

    private val _cvpResponse = MutableLiveData<CurrentVsPredictedResponse>()
    val cvpResponse : LiveData<CurrentVsPredictedResponse> = _cvpResponse

    private val _err = MutableLiveData<Int>()
    val err : LiveData<Int> = _err

    fun getClothing(city : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofit = NetworkHandler.getRetrofit("http://10.0.2.2:8090")

                val service: ClothesService = retrofit.create(ClothesService::class.java)

                val result = service.getClothesResponse(city).execute()

                if (result.isSuccessful){
                    _clothingResponse.postValue(result.body())
                }else{
                    _err.postValue(R.string.error_occured)
                }
            }catch (ex : IOException){
                ex.toString()
                _err.postValue(R.string.error_con)
            }
        }
    }

    fun getCVP(city : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofit = NetworkHandler.getRetrofit("http://10.0.2.2:9080")

                val service: CVPService = retrofit.create(CVPService::class.java)

                val result = service.getCVP(city).execute()

                if (result.isSuccessful){
                    _cvpResponse.postValue(result.body())
                }else{
                    _err.postValue(R.string.error_occured)
                }
            }catch (ex : IOException){
                _err.postValue(R.string.error_con)
            }
        }
    }
}