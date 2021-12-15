package com.tysia.microweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.tysia.microweather.data.model.ClothingResponse
import com.tysia.microweather.data.model.CurrentVsPredictedResponse
import com.tysia.microweather.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    var cvpResponse : CurrentVsPredictedResponse? = null
    var clothingResponse : ClothingResponse? = null

    private lateinit var binding: ActivityMainBinding

    var viewModel = CitiesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val city = intent.getStringExtra("city")!!

        viewModel.clothingResponse.observe(this) {
            clothingResponse = it
            showIfShould()
        }

        viewModel.cvpResponse.observe(this) {
            cvpResponse = it
            showIfShould()
        }

        viewModel.err.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.getClothing(city)
        viewModel.getCVP(city)

    }

    private fun showIfShould(){
        if (clothingResponse != null && cvpResponse != null){
            setUpBasics(cvpResponse!!)
            setUpComparison(cvpResponse!!)
            setUpClothes(clothingResponse!!)
            findViewById<ProgressBar>(R.id.progressBar2).visibility = View.GONE
        }
    }

    private fun setUpClothes(clo : ClothingResponse){
        val sb = StringBuilder()

        clo.adviceList!!.forEach {
            sb.append("Planując wyjście na ${it.hoursAhead} godz.\n")

            it.tips!!.forEachIndexed { index, tip ->
                sb.append("\t${index+1}. ")
                sb.append(tip.lowercase())

                if (it.tips!!.last() == tip){
                    sb.append(".\n\n")
                }else{
                    sb.append(",\n")
                }
            }
        }

        binding.clothesTv.text = sb.toString()
    }

    private fun setUpBasics(cvp : CurrentVsPredictedResponse){
        binding.actTemperature.text = cvp.currentTemp.toString() + "\u2103"
    }

    private fun setUpComparison(cvp : CurrentVsPredictedResponse){
        binding.tempActPlot.setValue(cvp.currentTemp!!.toInt())
        binding.tempPrePlot.setValue(cvp.predictedTemp!!.toInt())

        binding.ftempPlotAct.setValue(cvp.currentFeelsLike!!.toInt())
        binding.ftempPlotPre.setValue(cvp.predictedFeelsLike!!.toInt())

        binding.windPlotAct.setValue(cvp.predictedWindSpeed!!.toInt())
        binding.windPlotPre.setValue(cvp.predictedWindSpeed!!.toInt())

        binding.pressurePlotAct.setValue(cvp.currentPressure!!.toInt() / 100)
        binding.pressurePlotAct.setTitle(cvp.currentPressure!!.toInt())

        binding.pressurePlotPre.setValue(cvp.predictedPressure!!.toInt() / 100)
        binding.pressurePlotPre.setTitle(cvp.predictedPressure!!.toInt())
    }
}