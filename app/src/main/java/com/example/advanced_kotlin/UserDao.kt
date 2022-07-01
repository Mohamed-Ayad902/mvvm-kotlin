package com.example.advanced_kotlin

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from users_table")
    fun getAllUsers(): LiveData<List<User>>

}