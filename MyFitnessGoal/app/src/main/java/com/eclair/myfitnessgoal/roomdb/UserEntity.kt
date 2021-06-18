package com.eclair.myfitnessgoal.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User_Table")
data class UserEntity(
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "email") var emailId: String,
    @ColumnInfo(name = "uid") var uid: String,
    @ColumnInfo(name = "Password") var password: String,
    @ColumnInfo(name = "goal") var goal: String,
    @ColumnInfo(name = "activeness") var activeness: String,
    @ColumnInfo(name = "Sex") var sex: String,
    @ColumnInfo(name = "height") var height: String,
    @ColumnInfo(name = "weight") var weight: String,
    @ColumnInfo(name = "dob") var dob: String,
    @ColumnInfo(name = "reqCalorie") var reqCalorie: String,

    ) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}