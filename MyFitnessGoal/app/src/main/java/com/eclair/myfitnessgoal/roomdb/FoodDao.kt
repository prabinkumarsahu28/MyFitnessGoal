package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFood(foodEntity: FoodEntity)


    @Query("SELECT * FROM FoodTable")
    fun getAllFood(): LiveData<List<FoodEntity>>

    @Query("SELECT SUM(calories) FROM FoodTable")
    fun getTotalCalorie(): LiveData<Int?>

}