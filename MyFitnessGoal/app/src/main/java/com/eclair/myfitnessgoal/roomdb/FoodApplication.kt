package com.eclair.myfitnessgoal.roomdb

import android.app.Application

class FoodApplication : Application() {

    private val foodDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getFoodDao()

    }

    val foodRepo by lazy {
        FoodRepo(foodDao)
    }

    private val userDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getUserDao()
    }

    val userRepo by lazy {
        UserRepo(userDao)
    }

}