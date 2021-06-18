package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFood(foodEntity: FoodEntity)


    @Query("SELECT * FROM FoodTable")
    fun getAllFood(): LiveData<List<FoodEntity>>

    @Query("SELECT * FROM FoodTable WHERE curDate =:reqDate AND type =:types AND uid =:uid")
    fun getFoodDateWise(reqDate: String, types: String, uid: String): LiveData<List<FoodEntity>>

    @Query("SELECT SUM(calories) FROM FoodTable WHERE curDate =:reqDate AND type =:types AND uid =:uid")
    fun getFoodDateWiseCalSum(
        reqDate: String,
        types: String,
        uid: String,
    ): LiveData<Int?>

    @Query("SELECT SUM(calories) FROM FoodTable")
    fun getTotalCalorie(): LiveData<Int?>

    @Query("SELECT * FROM FoodTable WHERE type =:types")
    fun getAddedFood(types: String): LiveData<List<FoodEntity>>

    @Query("SELECT SUM(calories) FROM FoodTable WHERE id =:id AND type =:types")
    fun getCalTypeWise(id: String, types: String): LiveData<Int?>

    @Delete
    fun deleteFood(foodEntity: FoodEntity)
}