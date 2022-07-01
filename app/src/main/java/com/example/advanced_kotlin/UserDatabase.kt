package com.example.advanced_kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            val temp = INSTANCE
            if (temp != null)
                return temp

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "user_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}