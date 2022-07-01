package com.example.advanced_kotlin

import androidx.lifecycle.LiveData

class UserRepositoryImpl (private val dao: UserDao): UserRepository {

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return dao.getAllUsers()
    }
}