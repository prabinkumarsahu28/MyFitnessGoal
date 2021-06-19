package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {
    fun addData(userEntity: UserEntity) {
        userRepo.addUserDetails(userEntity)
    }

    fun getAllDetails(): LiveData<UserEntity> {
        return userRepo.getAllData()
    }

    fun getReqCalorie(uid: String?): String {
        return userRepo.getReqCalorie(uid)
    }
    fun getWeight(uid: String?): String {
        return userRepo.getWeight(uid)
    }
    fun getUserName(uid: String?): String {
        return userRepo.getUserName(uid)
    }

}