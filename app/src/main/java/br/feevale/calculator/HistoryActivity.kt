package br.feevale.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HistoryActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        val intent =  intent?.getStringArrayListExtra("CALCULATOR")
        println(intent?.get(0))
    }

    fun backToMainActivity(view: View) {
        finish()
    }
}