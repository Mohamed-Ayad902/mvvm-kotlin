package com.example.advanced_kotlin

import androidx.lifecycle.LiveData

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    fun getAllUsers(): LiveData<List<User>>

}