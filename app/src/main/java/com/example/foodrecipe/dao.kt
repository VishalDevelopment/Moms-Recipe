package com.example.foodrecipe

import androidx.room.Dao
import androidx.room.Query
@Dao
interface dao {
    @Query("SELECT * FROM recipe ")
    fun getAll():List<recipe?>?

}