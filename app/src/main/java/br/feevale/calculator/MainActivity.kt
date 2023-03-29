package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var value: String = ""
    val displayText: TextView = findViewById(R.id.display_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClickValue(view: View){
        val button: Button = view as Button
        value += button.text
        Log.d("TextButton", "${button.text} - ${displayText.text} - ${value}")
        displayText.text = value
    }

    fun getResult(view: View){

    }
}