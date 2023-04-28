package br.feevale.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class HistoryActivity : AppCompatActivity() {

    var historyContent = ""
    var historyText: TextView? = null
    private val line = "------------------------------"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        val intentContent =  intent?.getStringArrayListExtra("CALCULATOR")
        historyText = findViewById(R.id.historyText)
        intentContent?.forEach {
            historyContent += "\n$it"
            historyContent += "\n$line"
        }
        historyText?.text = historyContent
    }

    fun backToMainActivity(view: View) {
        finish()
    }
}