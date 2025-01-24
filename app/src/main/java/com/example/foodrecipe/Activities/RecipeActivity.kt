package com.example.foodrecipe.Activities

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foodrecipe.R
import com.example.foodrecipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeBinding
    var imgCrop = true
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ScrollUpButton()
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.foodImage)
        binding.tittle.text = intent.getStringExtra("tittle")
//        binding.ingData.text = intent.getStringExtra("ing")
        binding.stepsData.text = intent.getStringExtra("des")

        var ing = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
            ?.toTypedArray()

        binding.time.text = ing!!.get(0)

        for (i in 1 until ing.size){
            binding.ingData.text = """
                ${binding.ingData.text} 🟢 ${ing[i]}
                
            """.trimIndent()
        }

        binding.steps.background =null
        binding.steps.setTextColor(Color.BLACK)

        binding.steps.setOnClickListener {
            binding.steps.setTextColor(getColor(R.color.white))
            binding.ingredients.setTextColor(getColor(R.color.black))
            binding.ingScrollView.visibility = View.GONE
            binding.stepsScrollView.visibility = View.VISIBLE
            binding.ingredients.background =null
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
        }

        binding.ingredients.setOnClickListener {

            binding.ingredients.setTextColor(getColor(R.color.white))
            binding.ingredients.setBackgroundResource(R.drawable.btn_ing)
            binding.ingScrollView.visibility = View.VISIBLE

            binding.steps.setTextColor(getColor(R.color.black))
            binding.stepsScrollView.visibility = View.GONE
            binding.steps.background =null


        }

        binding.fullscreen.setOnClickListener {
           if (imgCrop==true){
               binding.foodImage.scaleType=ImageView.ScaleType.FIT_CENTER
               binding.blackishEffect.visibility = View.INVISIBLE
               binding.fullscreen.setColorFilter(Color.BLACK)
               Glide.with(this).load(intent.getStringExtra("img")).into(binding.foodImage)
               imgCrop =!imgCrop
           }
            else{
                binding.foodImage.scaleType = ImageView.ScaleType.CENTER_CROP
               binding.blackishEffect.visibility = View.VISIBLE
               binding.fullscreen.setColorFilter(Color.WHITE)
               Glide.with(this).load(intent.getStringExtra("img")).into(binding.foodImage)
               imgCrop =!imgCrop
           }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun ScrollUpButton() {
        var isExpanded = false

        val relativeLayout = findViewById<RelativeLayout>(R.id.relativeLayout)
        val scrollUpButton = findViewById<ImageView>(R.id.scroll_up)
        scrollUpButton.setOnClickListener {
            val density = resources.displayMetrics.density
            val layoutParams = relativeLayout.layoutParams

            if (!isExpanded) {
                val newHeightInDp = 700
                layoutParams.height = (newHeightInDp * density).toInt()
                isExpanded = true
                scrollUpButton.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
            } else {
                layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
                isExpanded = false

                scrollUpButton.setImageResource(R.drawable.scroll_up)
            }
            relativeLayout.layoutParams = layoutParams
        }
    }
}