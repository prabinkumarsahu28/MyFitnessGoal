package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepo(
    private val foodDao: FoodDao,
    private val exerciseDao: ExerciseDao,
    private val userDao: UserDao,
) {

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
    ): LiveData<Int?> {
        return foodDao.getFoodDateWiseCalSum(reqDate, types, uid)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodDao.getTotalCalorie()
    }

    fun getAddedFood(types: String): LiveData<List<FoodEntity>> {
        return foodDao.getAddedFood(types)
    }

    fun getCalDateWise(uid: String, reqDate: String): LiveData<Int?> {
        return foodDao.getCalDateWise(uid, reqDate)
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

    fun getAllData(): LiveData<UserEntity> {
        return userDao.getAllUserData()
    }

    fun getReqCalorie(uid: String?): LiveData<String> {
        return userDao.getReqCalorie(uid)
    }

    fun getWeight(uid: String?): String {
        return userDao.getWeight(uid)
    }

    fun getUserName(uid: String?): String {
        return userDao.getUserName(uid)
    }

    fun getUserEmail(uid: String?): String {
        return userDao.getUserEmail(uid)
    }

    fun getSex(uid: String?): String {
        return userDao.getSex(uid)
    }

    fun getGoal(uid: String?): String {
        return userDao.getGoal(uid)
    }

    fun getProfile(uid: String?): String {
        return userDao.getProfile(uid)
    }

    fun getHeight(uid: String?): String {
        return userDao.getHeight(uid)
    }

    fun getDob(uid: String?): String {
        return userDao.getDob(uid)
    }


    fun addExercise(exerciseEntity: ExerciseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseDao.addExercise(exerciseEntity)
        }
    }

    fun getExerciseCalories(uid: String, curDate: String): LiveData<Int?> {
        return exerciseDao.getExerciseCalories(uid, curDate)
    }

//    fun getUserDetails(uid: String?):LiveData<UserEntity>{
//        return userDao.getUserDetails(uid)
//    }
//
//    fun editProfile(userEntity: UserEntity?) {
//        CoroutineScope(Dispatchers.IO).launch {
//            userDao.editProfile(userEntity)
//        }
//    }
}