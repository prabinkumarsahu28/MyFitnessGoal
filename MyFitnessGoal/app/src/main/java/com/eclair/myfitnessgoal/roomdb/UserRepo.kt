package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepo(private val userDao: UserDao) {

    fun addUserDetails(userEntity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.addUserData(userEntity)
        }
    }

    fun getAllData() : LiveData<UserEntity> {
        return userDao.getAllUserData()
    }

    fun getReqCalorie(uid: String): String {
        return userDao.getReqCalorie(uid)
    }
}