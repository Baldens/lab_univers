package com.example.labone.day_planer.db

import java.util.*

class ModelPlanner(
    var id: Int = getAutoId(),
    var name: String = "",
    var description: String = ""
) {
    companion object{
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}