package com.musabagab.interviewtest.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val username: String?,
    val email: String?
)