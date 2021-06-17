package com.eclair.myfitnessgoal.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodEntity::class,UserEntity::class], version = 2)
abstract class FoodDb : RoomDatabase() {

    abstract fun getFoodDao(): FoodDao

    abstract fun getUserDao() : UserDao

    companion object {
        private var INSTANCE: FoodDb? = null

        fun getDbContext(context: Context): FoodDb {
            return if (INSTANCE == null) {
                val builder =
                    Room.databaseBuilder(context.applicationContext, FoodDb::class.java, "foodDB")
                        .fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }
}