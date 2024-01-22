package com.example.foodrecipe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.R
import com.example.foodrecipe.Data_Class.recipe

class SearchAdapter(var context: Context, var dataList: ArrayList<recipe>)
    :RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    inner class ViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.search_image)
        var txt = itemView.findViewById<TextView>(R.id.search_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_rv_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.img)
        holder.txt.text = dataList.get(position).tittle
    }

    //Required to work on filter
    fun filterList(filterList: ArrayList<recipe>){
        dataList= filterList
        notifyDataSetChanged()
    }

}