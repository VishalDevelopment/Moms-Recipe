package com.example.foodrecipe.Data_Class

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class recipe(
    var img:String,
    var tittle:String,
    var des:String,
    var ing:String,
    var category:String
){
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid =0
}