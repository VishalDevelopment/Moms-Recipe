package com.example.foodrecipe.Activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.Adapter.popularAdapter
import com.example.foodrecipe.AppDatabase
import com.example.foodrecipe.databinding.ActivityHomepageBinding
import com.example.foodrecipe.Data_Class.recipe
import com.example.foodrecipe.R

class Homepage : AppCompatActivity() {
    lateinit var binding: ActivityHomepageBinding
    lateinit var rvAdapter: popularAdapter
    lateinit var dataList: ArrayList<recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuHome.setOnClickListener {
            var dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_sheet)

            dialog.show()
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.BOTTOM)

            var privacy = dialog.findViewById<LinearLayout>(R.id.topLayout)
            var dev = dialog.findViewById<LinearLayout>(R.id.BottomLayout)

            dev.setOnClickListener{
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/vishalgoswami7714/"))
                startActivity(intent)
            }

            
        }
        setUpRecyclerView()
        binding.searchBar.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java)

            startActivity(intent)
        }

        binding.mainDish.setOnClickListener {
            intent = Intent(this, CategoryActivity::class.java)

            intent.putExtra("TITTLE","Main Dish")
            intent.putExtra("CATEGORY","Dish")
            startActivity(intent)
        }

        binding.drinks.setOnClickListener {
            intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITTLE","Drinks")
            intent.putExtra("CATEGORY","Drinks")
            startActivity(intent)
        }

        binding.dessart.setOnClickListener {
            intent = Intent(this, CategoryActivity::class.java)

            intent.putExtra("TITTLE","Dessert")
            intent.putExtra("CATEGORY","Dessert")
            startActivity(intent)
        }

        binding.salad.setOnClickListener {
            intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITTLE","Salad")
            intent.putExtra("CATEGORY","Salad")

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