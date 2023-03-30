package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var expression: String = ""
    var displayText: TextView? = null
    var calculate = Calculate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayText = findViewById(R.id.display_text) as TextView
    }

    fun buttonClickValue(view: View){
        val button: Button = view as Button

        if(listOf("+", "-", "*", "/").contains(button.text)){
            expression += " ${button.text} "
        } else {
            expression += button.text
        }
        Log.d("RESPONSE", expression)
        displayText?.text = expression
    }

    fun getResult(view: View){
        val response = calculate.calculate(expression)
        Log.d("RESPONSE", response)
        expression = response
        displayText?.setText(response)
    }

    fun clearResult(view: View){
        expression = ""
        displayText?.setText("0")
    }
}