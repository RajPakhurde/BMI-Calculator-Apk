package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightinput = findViewById<EditText>(R.id.weightInput)
        val heightinput = findViewById<EditText>(R.id.heightInput)
        val btnCal = findViewById<Button>(R.id.btnCalculate)


       btnCal.setOnClickListener {

           val weightValue = weightinput.text.toString()
           val heightValue = heightinput.text.toString()


           if(validate(weightValue,heightValue)) {
               val bmi = weightValue.toFloat()/((heightValue.toFloat()/100)*(heightValue.toFloat()/100))
               val bmi2digit = String.format("%.2f",bmi).toFloat()

               remarkValue(bmi2digit)
           }

       }


    }
    fun validate(weightValue:String? , heightValue:String?): Boolean{
        var resultCardview = findViewById<CardView>(R.id.resultCardview)
        when {
            weightValue.isNullOrEmpty() -> {
                Log.i("mytag","funditon is running")
                Toast.makeText(
                    this@MainActivity,
                    "Please enter the weight",
                    Toast.LENGTH_SHORT
                ).show()
                resultCardview.setVisibility(View.GONE)
                return false
            }
            heightValue.isNullOrEmpty() -> {
                Log.i("mytag","second funtion is running")
                Toast.makeText(
                    this@MainActivity,
                    "Please enter the height",
                    Toast.LENGTH_SHORT
                ).show()
                resultCardview.setVisibility(View.GONE)
                return false
            }
            else -> {
                resultCardview.setVisibility(View.VISIBLE)
                return true
            }
        }
    }

    fun remarkValue(bmi: Float){
        val remark = findViewById<TextView>(R.id.remark)
        val result = findViewById<TextView>(R.id.result)


        result.text = "BMI:- ${bmi.toString()}"

        var color= 0
        var remarktext = ""

        when{
            bmi < 18.6 ->{
                remarktext = "UnderWeight"
                color = R.color.underWeight
            }
            bmi.toDouble() in 18.6..24.9 ->{
                remarktext = "Healthy"
                color = R.color.healthy
            }
            bmi.toDouble() in 24.9..29.0 ->{
                remarktext = "OverWeight"
                color = R.color.overWeight
            }
            bmi.toDouble() > 29.0 ->{
                remarktext = "Obese"
                color = R.color.obese
            }

        }
        remark.text = remarktext
        remark.setTextColor(ContextCompat.getColor(this,color))
    }

}