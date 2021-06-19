package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserData(userEntity: UserEntity)

    @Query("SELECT * from User_Table")
    fun getAllUserData(): LiveData<UserEntity>

    @Query("SELECT reqCalorie FROM User_Table WHERE uid =:uid")
    fun getReqCalorie(uid: String?): LiveData<String>

    @Query("SELECT weight FROM User_Table WHERE uid =:uid")
    fun getWeight(uid: String?): String

    @Query("SELECT height FROM User_Table WHERE uid =:uid")
    fun getHeight(uid: String?): String

    @Query("SELECT dob FROM User_Table WHERE uid =:uid")
    fun getDob(uid: String?): String

    @Query("SELECT userName FROM User_Table WHERE uid =:uid")
    fun getUserName(uid: String?): String

    @Query("SELECT email FROM User_Table WHERE uid =:uid")
    fun getUserEmail(uid: String?): String

    @Query("SELECT Sex FROM User_Table WHERE uid =:uid")
    fun getSex(uid: String?): String

    @Query("SELECT goal FROM User_Table WHERE uid =:uid")
    fun getGoal(uid: String?): String

    @Query("SELECT profile FROM User_Table WHERE uid=:uid")
    fun getProfile(uid: String?): String

//    @Query("SELECT * FROM User_Table WHERE uid =:uid")
//    fun getUserDetails(uid: String?):LiveData<UserEntity>
//
//    @Update
//    fun editProfile(userEntity: UserEntity?)

}