package com.example.clickerapplication.model

import android.app.Application

class Points : Application(){
    companion object {

        var points = 0
        var pointsPerClick = 1
        var pointsPerSecond = 0
        var pointsForFirstBonusCookie = 80
        var firstBonus = 30
        var pointsForSecondBonusCookie = 150
        var secondBonus = 120
    }

}