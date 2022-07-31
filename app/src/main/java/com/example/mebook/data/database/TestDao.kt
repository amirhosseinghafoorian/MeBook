package com.example.mebook.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mebook.model.database.User

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Delete
    fun removeUser(user: User)

}