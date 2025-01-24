package com.example.foodrecipe.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.Adapter.SearchAdapter
import com.example.foodrecipe.AppDatabase
import com.example.foodrecipe.databinding.ActivitySearchBinding
import com.example.foodrecipe.Data_Class.recipe

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding

    lateinit var rvAdapter: SearchAdapter
    lateinit var dataList: ArrayList<recipe>
    lateinit var recipe: List<recipe?>

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBar.requestFocus()
        var db = Room.databaseBuilder(this, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()

        recipe = daoObject.getAll()!!

        setupRecyclerView()

//        required to work with text watcher to get view according to search item

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != "") {
                    filterData(p0.toString())
                } else {
                    setupRecyclerView()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.backBtn.setOnClickListener {
            finish()
        }

    }

    private fun filterData(filterText: String) {
        var filterData = arrayListOf<recipe>()
        for (i in recipe.indices) {
            if (recipe[i]!!.tittle.lowercase().contains(filterText.lowercase())) {
                filterData.add(recipe[i]!!)
            }
            rvAdapter.filterList(filterList = filterData)
        }
    }

    private fun setupRecyclerView() {
        dataList = arrayListOf()
        binding.searchRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        for (i in recipe.indices) {
            if (recipe[i]!!.category.contains("Popular")) {
                dataList.add(recipe[i]!!)
            }
        }

        rvAdapter = SearchAdapter(this, dataList) { selectedRecipe ->
            navigateToRecipeActivity(selectedRecipe)
        }
        binding.searchRv.adapter = rvAdapter
    }
    private fun navigateToRecipeActivity(recipe: recipe) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("img", recipe.img)
        intent.putExtra("tittle", recipe.tittle)
        intent.putExtra("des", recipe.des)
        intent.putExtra("ing", recipe.ing)
        startActivity(intent)
    }
}