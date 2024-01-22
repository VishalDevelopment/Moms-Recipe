package com.example.foodrecipe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.Data_Class.recipe
import com.example.foodrecipe.R

class CategoryAdapter(var context: Context , var dataList: ArrayList<recipe>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var photo = itemView.findViewById<ImageView>(R.id.list_img)
        var food_name = itemView.findViewById<TextView>(R.id.food_name)
        var time = itemView.findViewById<TextView>(R.id.time)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.category_rv,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.photo)
        holder.food_name.text = dataList.get(position).tittle
        var temp = dataList.get(position).ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.time.text = temp[0]
    }
}