package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM FoodTable")
    fun getAllFood(): LiveData<List<FoodEntity>>

}