package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserData(userEntity: UserEntity)

    @Query("SELECT * from User_Table")
    fun getAllUserData(): LiveData<UserEntity>

    @Query("SELECT reqCalorie FROM User_Table WHERE uid =:uid")
    fun getReqCalorie(uid: String?): String

    @Query("SELECT weight FROM User_Table WHERE uid =:uid")
    fun getWeight(uid: String?): String

    @Query("SELECT userName FROM User_Table WHERE uid =:uid")
    fun getUserName(uid: String?): String

}