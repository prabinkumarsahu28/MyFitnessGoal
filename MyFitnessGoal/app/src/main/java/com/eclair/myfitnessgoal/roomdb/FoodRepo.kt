package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepo(private val foodDao: FoodDao) {

    fun addFood(foodEntity: FoodEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.addFood(foodEntity)
        }
    }

    fun getAllFood(): LiveData<List<FoodEntity>> {
        return foodDao.getAllFood()
    }

    fun getFoodDateWise(reqDate: String, types: String, uid: String): LiveData<List<FoodEntity>> {
        return foodDao.getFoodDateWise(reqDate, types, uid)
    }

    fun getFoodDateWiseCalSum(
        reqDate: String,
        types: String,
        uid: String,
    ): LiveData<Int?>{
        return foodDao.getFoodDateWiseCalSum(reqDate, types, uid)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodDao.getTotalCalorie()
    }

    fun getAddedFood(types: String): LiveData<List<FoodEntity>> {
        return foodDao.getAddedFood(types)
    }

    fun getCalDateWise(uid: String, reqDate: String): LiveData<Int?>{
        return foodDao.getCalDateWise(uid, reqDate)
    }

    fun deleteFoodItem(foodEntity: FoodEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.deleteFood(foodEntity)
        }
    }
}