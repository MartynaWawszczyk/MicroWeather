package com.tysia.microweather

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.absoluteValue

class PlotyView( context : Context, attrs : AttributeSet)
    : ConstraintLayout(context, attrs) {

    val vPlotPlus : ProgressBar
    val vPlotMinus : ProgressBar
    val tempPlus : TextView

        init {
            inflate(context, R.layout.item_temperature_plot, this)

            vPlotPlus = findViewById<ProgressBar>(R.id.plot_plus)
            vPlotMinus = findViewById<ProgressBar>(R.id.plot_minus)
            tempPlus = findViewById<TextView>(R.id.temp_plus)
        }

    fun setValue(value : Int,){
        tempPlus.text = value.toString()

        var valueB = value

        if (valueB.absoluteValue < 10){
            valueB *= 10
        }

        while (valueB.absoluteValue > 50){
            valueB %= 10
        }

        if (valueB < 0){
            vPlotMinus.progress = valueB
        }else{
            vPlotPlus.progress = valueB
        }
    }

    fun setTitle(value : Int,){
        tempPlus.text = (value).toString()
    }
}