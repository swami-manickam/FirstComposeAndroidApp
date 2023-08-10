package com.mycompose.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mycompose.android.data.model.UserInfoModel


@Database(entities = [UserInfoModel::class], version = 0)

abstract class ComposeDatabase : RoomDatabase() {


    companion object {
        private const val DB_NAME = "user_database.db"

        @Volatile
        private var instance: ComposeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ComposeDatabase::class.java, DB_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}