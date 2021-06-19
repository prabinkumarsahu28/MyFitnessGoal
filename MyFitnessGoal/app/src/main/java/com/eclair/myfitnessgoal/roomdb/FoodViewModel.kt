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

    fun getFoodDateWise(reqDate: String, types: String, uid: String): LiveData<List<FoodEntity>> {
        return foodRepo.getFoodDateWise(reqDate, types, uid)
    }

    fun getFoodDateWiseCalSum(
        reqDate: String,
        types: String,
        uid: String,
    ): LiveData<Int?>{
        return foodRepo.getFoodDateWiseCalSum(reqDate, types, uid)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodRepo.getTotalCalorie()
    }

    fun getALlAddedFood(type: String): LiveData<List<FoodEntity>> {
        return foodRepo.getAddedFood(type)
    }

    fun getCalDateWise(uid: String, reqDate: String): LiveData<Int?>{
        return foodRepo.getCalDateWise(uid, reqDate)
    }

    fun getDeletedFoodItem(foodEntity: FoodEntity) {
        foodRepo.deleteFoodItem(foodEntity)
    }


    fun addExercise(exerciseEntity: ExerciseEntity){
        foodRepo.addExercise(exerciseEntity)
    }

    fun getExerciseCalories(uid:String):LiveData<Int?>{
        return foodRepo.getExerciseCalories(uid)
    }
}