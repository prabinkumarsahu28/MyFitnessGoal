package com.eclair.myfitnessgoal.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ExerciseTable")
class ExerciseEntity(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "cal") var cal: String?,
    @ColumnInfo(name = "curDate") var curDate: String?,
    @ColumnInfo(name = "uid") var uid: String?,
):Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}