package com.example.clickerapplication

import android.support.v7.widget.RecyclerView
import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Toast
import com.example.clickerapplication.model.Bonus
import kotlinx.android.synthetic.main.bonus_row.view.*
import com.example.clickerapplication.model.Points.Companion.points
import com.example.clickerapplication.model.Points.Companion.pointsPerClick
import com.example.clickerapplication.model.Points.Companion.pointsPerSecond
import kotlin.math.roundToInt


class RecylerViewAdapter(val items : ArrayList<Bonus>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bonusTitle?.text = items.get(position).title
        holder.cost?.text = items.get(position).cost.toString()
        if( position == 0) holder.pointsPerSeconds.text = items.get(position).pointsPerSecond.toString() + "/ppc"
        else holder.pointsPerSeconds.text = items.get(position).pointsPerSecond.toString() + "/pps"
        holder.button?.setOnClickListener {
            when( position) {
               0 -> {
                   buyItemWithPointsPerClick(items[position].cost, position)
                   notifyItemChanged(position)
               }
                1 -> {
                    val haveMoney = buyItemWithPointsPerSecond(items[position].cost, position)
                    if( haveMoney) pointsPerSecond++
                    notifyItemChanged(position)
                }
                2 -> {
                    val haveMoney = buyItemWithPointsPerSecond(items[position].cost, position)
                    if( haveMoney) pointsPerSecond+=8
                    notifyItemChanged(position)
                }
                3 -> {
                    val haveMoney = buyItemWithPointsPerSecond(items[position].cost, position)
                    if( haveMoney) pointsPerSecond+=47
                    notifyItemChanged(position)
                }
                4 -> {
                    val haveMoney = buyItemWithPointsPerSecond(items.get(position).cost, position)
                    if( haveMoney) pointsPerSecond+=260
                    notifyItemChanged(position)
                }
                5 -> {
                    val haveMoney = buyItemWithPointsPerSecond(items.get(position).cost, position)
                    if( haveMoney) pointsPerSecond+=1400
                    notifyItemChanged(position)
                }
            }
        }
    }

    private fun buyItemWithPointsPerClick(cost: Int, position: Int){
        if( points >= cost) {
            pointsPerClick++
            points -= cost
            println(items[position].cost)
            items[position].cost = (items[position].cost*2)
        }
        else {
            Toast.makeText(context,"Make more money!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun buyItemWithPointsPerSecond(cost: Int, position: Int): Boolean{
        if( points >= cost) {
            points -= cost
            items[position].cost = (items[position].cost*1.15).roundToInt()
            return true
        }
        else {
            Toast.makeText(context,"Make more money!",Toast.LENGTH_SHORT).show()
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.example.clickerapplication.R.layout.bonus_row, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val bonusTitle = view.bonusTitle
    val cost = view.bonusCost
    val button = view.buyButton
    val pointsPerSeconds = view.pointsPerSecond

}