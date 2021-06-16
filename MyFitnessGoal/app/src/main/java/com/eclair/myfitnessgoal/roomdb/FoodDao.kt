package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFood(foodEntity: FoodEntity)


    @Query("SELECT * FROM FoodTable")
    fun getAllFood(): LiveData<List<FoodEntity>>

    @Query("SELECT SUM(calories) FROM FoodTable")
    fun getTotalCalorie(): LiveData<Int?>

    @Query("SELECT * FROM FoodTable WHERE type =:types")
    fun getAddedFood(types: String): LiveData<List<FoodEntity>>

    @Delete
    fun deleteFood(foodEntity: FoodEntity)
}