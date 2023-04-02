package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.feevale.calculator.Operation.Companion.isOperation

class MainActivity : AppCompatActivity() {

    var expression: String = ""
    var displayText: TextView? = null
    var textError: TextView? = null
    var calculate = Calculate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayText = findViewById<TextView>(R.id.display_text)
        textError = findViewById<TextView>(R.id.text_error)
    }

    fun buttonClickValue(view: View) {
        val button: Button = view as Button

        val buttonText = button.text.toString()

        if (hasDot(buttonText))
            return

        expression += if (isOperation(buttonText)) " $buttonText "
        else buttonText

        displayText?.text = expression
    }

    private fun hasDot(buttonText: String): Boolean {
        val expressionSplit = expression.split(" ")
        return buttonText == "." && expression.contains(".") && expressionSplit[expressionSplit.size - 1].contains(".")
    }

    fun getResult(view: View) {
        try {
            val response = calculate.execute(expression)
            expression = response
            displayText?.text = response
            textError?.text = ""
        } catch (error: RuntimeException) {
            expression = ""
            displayText?.text = "0"
            textError?.text = error.message
        }
    }

    fun clearResult(view: View) {
        expression = ""
        displayText?.text = "0"
        textError?.text = ""
    }
}


