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
    ): LiveData<Int?> {
        return foodRepo.getFoodDateWiseCalSum(reqDate, types, uid)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return foodRepo.getTotalCalorie()
    }

    fun getALlAddedFood(type: String): LiveData<List<FoodEntity>> {
        return foodRepo.getAddedFood(type)
    }

    fun getCalDateWise(uid: String, reqDate: String): LiveData<Int?> {
        return foodRepo.getCalDateWise(uid, reqDate)
    }

    fun getDeletedFoodItem(foodEntity: FoodEntity) {
        foodRepo.deleteFoodItem(foodEntity)
    }


    fun addData(userEntity: UserEntity) {
        foodRepo.addUserDetails(userEntity)
    }

    fun getReqCalorie(uid: String?): LiveData<String> {
        return foodRepo.getReqCalorie(uid)
    }

    fun getWeight(uid: String?): String {
        return foodRepo.getWeight(uid)
    }

    fun getUserName(uid: String?): String {
        return foodRepo.getUserName(uid)
    }

    fun getUserEmail(uid: String?): String {
        return foodRepo.getUserEmail(uid)
    }

    fun getSex(uid: String?): String {
        return foodRepo.getSex(uid)
    }

    fun getGoal(uid: String?): String {
        return foodRepo.getGoal(uid)
    }

    fun getProfile(uid: String?): String {
        return foodRepo.getProfile(uid)
    }

    fun getHeight(uid: String?): String {
        return foodRepo.getHeight(uid)
    }

    fun getDob(uid: String?): String {
        return foodRepo.getDob(uid)
    }


    fun addExercise(exerciseEntity: ExerciseEntity) {
        foodRepo.addExercise(exerciseEntity)
    }

    fun getExerciseCalories(uid: String, curDate: String): LiveData<Int?> {
        return foodRepo.getExerciseCalories(uid, curDate)
    }

    fun getUserDetails(uid: String?):LiveData<UserEntity>{
        return foodRepo.getUserDetails(uid)
    }

    fun editProfile( height: String?, uid:String?) {
        foodRepo.editProfile(height, uid)
    }
}