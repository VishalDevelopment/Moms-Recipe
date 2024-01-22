package com.example.foodrecipe

import androidx.room.Dao
import androidx.room.Query
import com.example.foodrecipe.Data_Class.recipe

@Dao
interface dao {
    @Query("SELECT * FROM recipe ")
    fun getAll():List<recipe?>?

}