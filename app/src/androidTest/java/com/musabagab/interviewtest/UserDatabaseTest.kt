package com.musabagab.interviewtest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.musabagab.interviewtest.Database.UserDao
import com.musabagab.interviewtest.Database.UserDatabase
import com.musabagab.interviewtest.Database.UserEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest : TestCase() {

    private lateinit var userDao: UserDao
    private lateinit var db: UserDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserDatabase::class.java
        ).build()
        userDao = db.getUserDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadUser() = runBlocking {
        val user = UserEntity(
            UUID.randomUUID().toString(),
            "musab",
            "musab@gmail.com"
        )
        userDao.insert(user)

        val users = userDao.getAllUsers()
        assert(users.contains(user))
    }


}