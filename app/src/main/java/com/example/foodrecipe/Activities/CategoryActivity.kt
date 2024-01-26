package com.example.foodrecipe.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.Adapter.CategoryAdapter
import com.example.foodrecipe.AppDatabase
import com.example.foodrecipe.Data_Class.recipe
import com.example.foodrecipe.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryBinding
    lateinit var dataList: ArrayList<recipe>
    lateinit var rvAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleCategory.text = intent.getStringExtra("TITTLE")
        binding.backBtn.setOnClickListener {
            finish()
        }
        setUpRecyclerView()
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()

       binding.rvCategory.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        var db = Room.databaseBuilder(this, AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject=db.getDao()
        var recipes=daoObject.getAll()

        for (i in recipes!!.indices){
            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                dataList.add(recipes[i]!!)
            }
            rvAdapter = CategoryAdapter(this,dataList)
            binding.rvCategory.adapter = rvAdapter
        }

    }
}