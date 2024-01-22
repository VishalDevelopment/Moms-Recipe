package com.example.foodrecipe.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.Adapter.popularAdapter
import com.example.foodrecipe.AppDatabase
import com.example.foodrecipe.databinding.ActivityHomepageBinding
import com.example.foodrecipe.Data_Class.recipe

class Homepage : AppCompatActivity() {
    lateinit var binding: ActivityHomepageBinding
    lateinit var rvAdapter: popularAdapter
    lateinit var dataList: ArrayList<recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        binding.searchBar.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db = Room.databaseBuilder(this, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

       var daoObject=db.getDao()
        var recipes=daoObject.getAll()

        for (i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter = popularAdapter(dataList,this)
            binding.rvPopular.adapter = rvAdapter
        }

    }
}