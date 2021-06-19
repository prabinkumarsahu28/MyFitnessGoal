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

    val foodRepo by lazy {
        FoodRepo(foodDao, exerciseDao)
    }

    private val userDao by lazy {
        val database = FoodDb.getDbContext(this)
        database.getUserDao()
    }

    val userRepo by lazy {
        UserRepo(userDao)
    }

}