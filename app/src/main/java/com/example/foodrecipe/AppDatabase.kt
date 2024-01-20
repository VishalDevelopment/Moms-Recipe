package com.example.foodrecipe

import androidx.room.Dao
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [recipe::class] , exportSchema = false , version = 1)
abstract class AppDatabase():RoomDatabase() {

    abstract fun  getDao():dao

}