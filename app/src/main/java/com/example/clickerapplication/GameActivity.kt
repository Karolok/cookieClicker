package com.example.clickerapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import com.example.clickerapplication.model.Points.Companion.points
import com.example.clickerapplication.model.Points.Companion.pointsPerClick
import com.example.clickerapplication.model.Points.Companion.pointsPerSecond
import com.example.clickerapplication.model.Points.Companion.pointsForFirstBonusCookie
import com.example.clickerapplication.model.Points.Companion.pointsForSecondBonusCookie
import com.example.clickerapplication.model.Points.Companion.firstBonus
import com.example.clickerapplication.model.Points.Companion.secondBonus

class GameActivity : AppCompatActivity() {


    lateinit var pointsTextView: TextView
    lateinit var perSecondTextView: TextView
    lateinit var buttonClicker: ImageButton
    lateinit var shopButton: ImageButton
    lateinit var animation: Animation
    lateinit var firstBonusCookie: ImageButton
    lateinit var secondBonusCookie: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)
        val tStart = System.currentTimeMillis()
        var gameActive = true

        firstBonusCookie = findViewById(R.id.firstBonusCookie)
        secondBonusCookie = findViewById(R.id.secondBonusCookie)
        perSecondTextView = findViewById(R.id.perSecond)
        pointsTextView = findViewById(R.id.points)
        buttonClicker = findViewById(R.id.cookieButton)
        shopButton = findViewById(R.id.sShop)
        animation = AnimationUtils.loadAnimation(this, R.anim.cookie_size)

        pointsTextView.text = resources.getString(R.string.points, points)
        perSecondTextView.text = resources.getString(R.string.pointsPerSecond, pointsPerSecond)


        Thread(Runnable {
            while(true) {
                points += pointsPerSecond
                this@GameActivity.runOnUiThread {
                    pointsTextView.text = resources.getString(R.string.points, points)
                    perSecondTextView.text = resources.getString(R.string.pointsPerSecond, pointsPerSecond)
                }
                Thread.sleep(1000)
            }
        }).start()

        Thread(Runnable {
            while(true) {
                if ( points >= pointsForFirstBonusCookie) {
                    this@GameActivity.runOnUiThread {
                        firstBonusCookie.visibility = View.VISIBLE
                    }
                    pointsForFirstBonusCookie *= 3
                }
                else if ( points >= pointsForSecondBonusCookie) {
                    this@GameActivity.runOnUiThread {
                        secondBonusCookie.visibility = View.VISIBLE
                    }
                    pointsForSecondBonusCookie *= 3
                }
            }
        }).start()

        Thread(Runnable {
            while(gameActive) {
                if( points >= 1500) {
                    val tEnd = System.currentTimeMillis()
                    val tDelta = tEnd - tStart
                    val elapsedSeconds = tDelta / 1000.0
                    val intent = Intent(this@GameActivity, FinishActivity::class.java)
                    intent.putExtra("time", elapsedSeconds)
                    startActivity(intent)
                    gameActive = false
                }
            }
        }).start()

        firstBonusCookie.setOnClickListener{
            points += firstBonus
            firstBonus *= 2
            pointsTextView.text = resources.getString(R.string.points, points)
            firstBonusCookie.visibility = View.GONE
        }

        secondBonusCookie.setOnClickListener {
            points += secondBonus
            secondBonus *= 2
            pointsTextView.text = resources.getString(R.string.points, points)
            secondBonusCookie.visibility = View.GONE
        }

        buttonClicker.setOnClickListener {
            points += pointsPerClick
            pointsTextView.text = resources.getString(R.string.points, points)
            buttonClicker.startAnimation(animation)
        }

        shopButton.setOnClickListener {
            val intent = Intent(this@GameActivity, BonusActivity::class.java)
            startActivity(intent)
        }

    }

}
