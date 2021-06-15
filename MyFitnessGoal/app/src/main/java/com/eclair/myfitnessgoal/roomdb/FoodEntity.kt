package com.eclair.myfitnessgoal.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "FoodTable")
data class FoodEntity(
    @ColumnInfo(name = "foodName") var foodName: String,
    @ColumnInfo(name = "quantity") var quantity: String,
    @ColumnInfo(name = "calories") var calories: String,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}