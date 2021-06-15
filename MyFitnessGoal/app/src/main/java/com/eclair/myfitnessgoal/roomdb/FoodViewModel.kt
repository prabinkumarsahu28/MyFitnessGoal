package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FoodViewModel(private val foodRepo: FoodRepo) : ViewModel() {

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