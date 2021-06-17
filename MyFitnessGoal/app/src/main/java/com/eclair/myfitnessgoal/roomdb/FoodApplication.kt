package com.eclair.myfitnessgoal.roomdb

import android.app.Application

class FoodApplication : Application() {

    private val foodDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getFoodDao()

    }
    private val userDao by lazy {
        val data = FoodDb.getDbContext(this)
        data.getUserDao()
    }

    val foodRepo by lazy {
        FoodRepo(foodDao,userDao)
    }
}