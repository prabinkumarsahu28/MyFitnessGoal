package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepo(private val foodDao: FoodDao, private val userDao: UserDao) {

    fun addFood(foodEntity: FoodEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.addFood(foodEntity)
        }
    }

    fun getAllFood(): LiveData<List<FoodEntity>> {
        return foodDao.getAllFood()
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodDao.getTotalCalorie()
    }

    fun getAddedFood(types: String): LiveData<List<FoodEntity>> {
        return foodDao.getAddedFood(types)
    }

    fun deleteFoodItem(foodEntity: FoodEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.deleteFood(foodEntity)
        }
    }

    fun addUserDetails(userEntity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.addUserData(userEntity)
        }
    }

    fun getAllData() : LiveData<UserEntity>{
        return userDao.getAllUserData()
    }
}