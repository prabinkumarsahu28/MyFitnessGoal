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

    fun getFoodDateWise(reqDate: String, types: String): LiveData<List<FoodEntity>> {
        return foodRepo.getFoodDateWise(reqDate, types)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodRepo.getTotalCalorie()
    }

    fun getALlAddedFood(type: String): LiveData<List<FoodEntity>> {
        return foodRepo.getAddedFood(type)
    }

    fun getDeletedFoodItem(foodEntity: FoodEntity) {
        foodRepo.deleteFoodItem(foodEntity)
    }
}