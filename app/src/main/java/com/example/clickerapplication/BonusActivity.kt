package com.example.clickerapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import com.example.clickerapplication.model.Bonus
import kotlinx.android.synthetic.main.activity_bonus.*

class BonusActivity : AppCompatActivity() {

    val bonusList: ArrayList<Bonus> = ArrayList()
    lateinit var backFromShop: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonus)
        addBonuses()
        bonus_list.layoutManager = LinearLayoutManager(this)
        bonus_list.adapter = RecylerViewAdapter(bonusList, this)



        backFromShop = findViewById(R.id.bShop)
        backFromShop.setOnClickListener {
            val intent = Intent(this@BonusActivity, GameActivity::class.java);
            startActivity(intent)
        }

    }
    private fun addBonuses() {
        val pointsPerClick = Bonus("Points per click", 100, 1)
        bonusList.add(pointsPerClick)
        val grandma = Bonus("Grandma", 100, 1)
        bonusList.add(grandma)
        val factory = Bonus("Factory", 1100, 8)
        bonusList.add(factory)
        val temple = Bonus("Temple", 5000, 47)
        bonusList.add(temple)
        val farm = Bonus("Farm", 12000, 260)
        bonusList.add(farm)
        val bank = Bonus("Bank", 30000, 1400)
        bonusList.add(bank)

    }
}
