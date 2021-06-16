package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.roomdb.FoodEntity

interface FoodClickListener {
    fun onFoodItemClicked(foodEntity: FoodEntity, s: String)
}