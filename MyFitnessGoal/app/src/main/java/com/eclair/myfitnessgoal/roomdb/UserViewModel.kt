package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val foodRepo: FoodRepo) : ViewModel() {
    fun addData(userEntity: UserEntity) {
        foodRepo.addUserDetails(userEntity)
    }

    fun getAllDetails(): LiveData<UserEntity> {
        return foodRepo.getAllData()
    }

}