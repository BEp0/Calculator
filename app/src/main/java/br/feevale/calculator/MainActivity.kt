package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        // TODO: não deixar dividir por zero,
        //  não deixar fazer algo assim: .2 * 2 (.2 esta errado)
        if(OperationUtils.isOperation(button.text.toString())){
            expression += " ${button.text} "
        } else {
            expression += button.text
        }
        displayText?.text = expression
    }

    fun getResult(view: View){
        val response = calculate.calculate(expression)
        expression = response
        displayText?.setText(response)
    }

    fun clearResult(view: View){
        expression = ""
        displayText?.setText("0")
    }
}