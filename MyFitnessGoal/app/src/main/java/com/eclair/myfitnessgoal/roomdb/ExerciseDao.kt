package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addExercise(exerciseEntity: ExerciseEntity)

    @Query("SELECT SUM(cal) FROM ExerciseTable WHERE uid =:uid")
    fun getExerciseCalories(uid:String):LiveData<Int?>

}