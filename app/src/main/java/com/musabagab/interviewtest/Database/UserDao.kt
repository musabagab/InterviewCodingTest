package com.musabagab.interviewtest.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}