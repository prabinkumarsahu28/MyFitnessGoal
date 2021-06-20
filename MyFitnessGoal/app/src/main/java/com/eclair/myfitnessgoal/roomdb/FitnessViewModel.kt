package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FitnessViewModel(private val fitnessRepo: FitnessRepo) : ViewModel() {

    fun addFood(foodEntity: FoodEntity) {
        fitnessRepo.addFood(foodEntity)
    }


    fun getAllFood(): LiveData<List<FoodEntity>> {
        return fitnessRepo.getAllFood()
    }

    fun getFoodDateWise(reqDate: String, types: String, uid: String): LiveData<List<FoodEntity>> {
        return fitnessRepo.getFoodDateWise(reqDate, types, uid)
    }

    fun getFoodDateWiseCalSum(
        reqDate: String,
        types: String,
        uid: String,
    ): LiveData<Int?> {
        return fitnessRepo.getFoodDateWiseCalSum(reqDate, types, uid)
    }

    fun getTotalCalorie(): LiveData<Int?> {
        return fitnessRepo.getTotalCalorie()
    }

    fun getALlAddedFood(type: String): LiveData<List<FoodEntity>> {
        return fitnessRepo.getAddedFood(type)
    }

    fun getCalDateWise(uid: String, reqDate: String): LiveData<Int?> {
        return fitnessRepo.getCalDateWise(uid, reqDate)
    }

    fun getDeletedFoodItem(foodEntity: FoodEntity) {
        fitnessRepo.deleteFoodItem(foodEntity)
    }


    fun addData(userEntity: UserEntity) {
        fitnessRepo.addUserDetails(userEntity)
    }

    fun getReqCalorie(uid: String?): LiveData<String> {
        return fitnessRepo.getReqCalorie(uid)
    }

    fun getWeight(uid: String?): String {
        return fitnessRepo.getWeight(uid)
    }

    fun getUserName(uid: String?): String {
        return fitnessRepo.getUserName(uid)
    }

    fun getUserEmail(uid: String?): String {
        return fitnessRepo.getUserEmail(uid)
    }

    fun getSex(uid: String?): String {
        return fitnessRepo.getSex(uid)
    }

    fun getGoal(uid: String?): String {
        return fitnessRepo.getGoal(uid)
    }

    fun getProfile(uid: String?): String {
        return fitnessRepo.getProfile(uid)
    }

    fun getHeight(uid: String?): String {
        return fitnessRepo.getHeight(uid)
    }

    fun getDob(uid: String?): String {
        return fitnessRepo.getDob(uid)
    }


    fun addExercise(exerciseEntity: ExerciseEntity) {
        fitnessRepo.addExercise(exerciseEntity)
    }

    fun getExerciseCalories(uid: String, curDate: String): LiveData<Int?> {
        return fitnessRepo.getExerciseCalories(uid, curDate)
    }

    fun getUserDetails(uid: String?):LiveData<UserEntity>{
        return fitnessRepo.getUserDetails(uid)
    }

    fun editProfile( height: String?, uid:String?) {
        fitnessRepo.editProfile(height, uid)
    }

    fun updateProfile(userEntity: UserEntity){
        fitnessRepo.updateProfile(userEntity)
    }
}