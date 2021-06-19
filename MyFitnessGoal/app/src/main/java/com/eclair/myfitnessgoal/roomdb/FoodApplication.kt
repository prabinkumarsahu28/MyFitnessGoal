package com.eclair.myfitnessgoal.roomdb

import android.app.Application

class FoodApplication : Application() {

    private val foodDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getFoodDao()

    }

    private val exerciseDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getExerciseDao()
    }

    private val userDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getUserDao()
    }

    val foodRepo by lazy {
        FoodRepo(foodDao, exerciseDao, userDao)
    }

}