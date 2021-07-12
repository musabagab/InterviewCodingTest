package com.musabagab.interviewtest.Repository

import com.musabagab.interviewtest.Database.UserDao
import com.musabagab.interviewtest.Database.UserEntity

class UserDatabaseRepository(private val userDao: UserDao) {

    suspend fun insertUser(userEntity: UserEntity) = userDao
        .insert(userEntity)

}