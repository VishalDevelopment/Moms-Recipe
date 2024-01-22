package com.example.foodrecipe

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodrecipe.Data_Class.recipe

@Database(entities = [recipe::class] , exportSchema = false , version = 1)
abstract class AppDatabase():RoomDatabase() {

    abstract fun  getDao():dao

}