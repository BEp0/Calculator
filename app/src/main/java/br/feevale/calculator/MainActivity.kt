package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.feevale.calculator.OperationUtils.Companion.isOperation

class MainActivity : AppCompatActivity() {

    var expression: String = ""
    var displayText: TextView? = null
    var calculate = Calculate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayText = findViewById(R.id.display_text) as TextView
    }

    fun buttonClickValue(view: View) {
        val button: Button = view as Button

        val buttonText = button.text.toString()

        // TODO: não deixar dividir por zero,
//        if (checkDivByZero(buttonText)){
//            throw java.lang.RuntimeException
//        }
        //  não deixar fazer algo assim: .2 * 2 (.2 esta errado)
//        if (checkDot(buttonText)){
//            throw java.lang.RuntimeException
//        }
        expression += if (isOperation(buttonText)) {
            " $buttonText "
        } else {
            buttonText
        }
        displayText?.text = expression
    }

//    private fun checkDot(buttonText: String) = buttonText == "." && expression.isBlank()

    fun getResult(view: View) {
        val response = calculate.execute(expression)
        expression = response
        displayText?.text = response
    }

    fun clearResult(view: View) {
        expression = ""
        displayText?.text = "0"
    }
}