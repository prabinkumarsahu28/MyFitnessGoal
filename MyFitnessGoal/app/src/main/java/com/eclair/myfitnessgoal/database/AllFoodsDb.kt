package com.eclair.myfitnessgoal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//provide all the tables we have use and version number
@Database(entities = [AllFoodsEntity::class], version = 1)
abstract class AllFoodsDb : RoomDatabase() {

    abstract fun getFoodItemsDao(): AllFoodsDao

    companion object {
        private var INSTANCE: AllFoodsDb? = null

        fun getDatabase(context: Context): AllFoodsDb {
            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    AllFoodsDb::class.java,
                    "AllFoods_Db"
                )

                builder.fallbackToDestructiveMigration()

                INSTANCE = builder.build()

                return INSTANCE!!

            } else {
                return INSTANCE!!
            }
        }
    }
}