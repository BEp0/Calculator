package br.feevale.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var expression: String = ""
    val defaultErrorMessage = "Erro ao tentar calcular expressão $expression"
    var displayText: TextView? = null
    var textError: TextView? = null
    val operation = Operation()
    val expressions = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayText = findViewById(R.id.display_text)
        textError = findViewById(R.id.text_error)
    }

    fun buttonClickValue(view: View) {

        val button: Button = view as Button
        val buttonText = button.text.toString()

        if (hasDot(buttonText)) return
        if (hasOperation(buttonText)) return

        expression += if (operation.isOperation(buttonText)) " $buttonText "
        else buttonText
        displayText?.text = expression
    }

    fun getResult(view: View) {
        try {
            val response = operation.execute(expression)
            expressions.add(formatToHistory(response))
            expression = response
            displayText?.text = response
            textError?.text = ""
        } catch (divisionByZeroException: DivisionByZeroException) {
            expression = ""
            displayText?.text = ""
            textError?.text = divisionByZeroException.message
        } catch (error: RuntimeException) {
            textError?.text = defaultErrorMessage
            displayText?.text = ""
            expression = ""
        }
    }

    private fun formatToHistory(response: String) =
        "${expression.replaceFirst(" ", "\n")}\n= $response"

    fun clearResult(view: View) {
        expression = ""
        displayText?.text = ""
        textError?.text = ""
    }

    fun getHistoryActivity(view: View) {
        val intent = Intent(baseContext, HistoryActivity::class.java)
        intent.putExtra("CALCULATOR", expressions)
        startActivity(intent)
    }

    private fun hasOperation(buttonText: String): Boolean {
        return operation.isOperation(buttonText) && (
                expression.contains(OperationEnum.SUM.type) ||
                        expression.contains(OperationEnum.SUB.type) ||
                        expression.contains(OperationEnum.MULT.type) ||
                        expression.contains(OperationEnum.DIV.type)
                )
    }

    private fun hasDot(buttonText: String): Boolean {
        val expressionSplit = expression.split(" ")
        return buttonText == "." && expression.contains(".") && expressionSplit[expressionSplit.size - 1].contains(
            "."
        )
    }
}


