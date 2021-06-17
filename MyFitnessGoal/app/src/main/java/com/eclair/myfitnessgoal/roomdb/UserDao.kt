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

    @Query("SELECT * from USER_TABLE")
    fun getAllUserData() : LiveData<UserEntity>

}