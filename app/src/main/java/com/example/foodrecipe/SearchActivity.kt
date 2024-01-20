package com.example.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding

    lateinit var rvAdapter: SearchAdapter
    lateinit var dataList: ArrayList<recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBar.requestFocus()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        dataList= arrayListOf()

        binding.searchRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        var db = Room.databaseBuilder(this,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipe = daoObject.getAll()

        for( i in recipe!!.indices){
            if(recipe[i]!!.category.contains("Popular")){
                dataList.add(recipe[i]!!)
            }
            rvAdapter= SearchAdapter(this,dataList)
            binding.searchRv.adapter= rvAdapter
        }

    }
}