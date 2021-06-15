package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData

class FoodViewModel(private val foodRepo: FoodRepo) {

    fun addFood(foodEntity: FoodEntity) {
        foodRepo.addFood(foodEntity)
    }

    fun getAllFood(): LiveData<List<FoodEntity>> {
        return foodRepo.getAllFood()
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodRepo.getTotalCalorie()
    }
}