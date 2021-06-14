package com.eclair.myfitnessgoal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AllFoodsDao {

    //to insert the values in the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFoodData(allFoodsEntity: AllFoodsEntity)

    //providing query to fetch data from table
    @Query("select*from all_foods_table")
    fun getFoodItems(): LiveData<List<AllFoodsEntity>>


}