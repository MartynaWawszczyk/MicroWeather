package com.tysia.microweather

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.tysia.microweather.data.model.ClothingResponse
import com.tysia.microweather.data.model.CurrentVsPredictedResponse

class CitiesListActivity : AppCompatActivity() {

    private val cities = listOf(
        "Poznań",
        "Wrocław",
        "Gdańsk",
        "Warszawa",
        "Kraków",
        "Łódź",
        "Bydgoszcz",
        "Toruń",
        "Katowice",
        "Szczecin",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)

        cities.forEach { addTV(it) }

    }


    private fun addTV(s : String){
        val tv = TextView(this)

        val scale = resources.displayMetrics.density
        val dpAsPixels = (10 * scale + 0.5f).toInt()
        tv.setPadding(dpAsPixels)

        tv.textSize = 16f
        tv.text = s

        tv.setTextColor(getColor(R.color.black))

        tv.setOnClickListener { goToMainScreen((cities.indexOf(s) +1).toString()) }

        findViewById<LinearLayout>(R.id.ll).addView(tv)
    }

    private fun goToMainScreen(city : String){
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("city", city)

        startActivity(intent)
    }
}