package com.eclair.myfitnessgoal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "all_foods_table")
class AllFoodsEntity(

    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    @ColumnInfo(name = "header") var header: String,
    @ColumnInfo(name = "name") var Itemname : String,
    @ColumnInfo(name = "quantity") var quantity : String,
    @ColumnInfo(name = "calories") var calories : String

    ) : Serializable {
}