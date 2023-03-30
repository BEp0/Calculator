package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var value: String = ""
    var displayText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayText = findViewById(R.id.display_text) as TextView
    }

    fun buttonClickValue(view: View){
        val button: Button = view as Button
        value += button.text
        Log.d("TextButton", "${button.text} - ${displayText?.text} - ${value}")
        displayText?.text = value
    }

    fun getResult(view: View){
        /**
         * pegar o value
         * exemplo -> 23 + 23 - 4
         *      vai ter um split passando depois um for com when onde vai
         *      iterar sobre um valor e setar o displayText no final
         */

    }

    fun clearResult(view: View){
        value = ""
        displayText?.setText("0")
    }
}