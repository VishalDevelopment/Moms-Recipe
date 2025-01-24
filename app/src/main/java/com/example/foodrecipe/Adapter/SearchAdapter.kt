package com.example.foodrecipe.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.Activities.RecipeActivity
import com.example.foodrecipe.R
import com.example.foodrecipe.Data_Class.recipe

class SearchAdapter(
    var context: Context,
    var dataList: ArrayList<recipe>,
    private val onItemClick: (recipe) -> Unit // Lambda for click handling
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.search_image)
        var txt = itemView.findViewById<TextView>(R.id.search_text)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(dataList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_rv_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].img).into(holder.img)
        holder.txt.text = dataList[position].tittle
    }

    fun filterList(filterList: ArrayList<recipe>) {
        dataList = filterList
        notifyDataSetChanged()
    }
}