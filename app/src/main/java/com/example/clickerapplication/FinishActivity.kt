package com.example.clickerapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.round

class FinishActivity : AppCompatActivity() {

    lateinit var finishTime: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        var time = this.intent.getDoubleExtra("time", -1.0)
        finishTime = findViewById(R.id.finishTime)
        finishTime.text = time.toString()

        findViewById<Button>(R.id.finishGameButton).setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }

    }
}
