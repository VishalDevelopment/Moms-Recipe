package com.example.foodrecipe.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipe.Data_Class.recipe
import com.example.foodrecipe.R
import com.example.foodrecipe.Activities.RecipeActivity

class CategoryAdapter(var context: Context , var dataList: ArrayList<recipe>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var photo = itemView.findViewById<ImageView>(R.id.list_img)
        var food_name = itemView.findViewById<TextView>(R.id.food_name)
        var time = itemView.findViewById<TextView>(R.id.time)
        var forward = itemView.findViewById<ImageView>(R.id.forward_btn)
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

        holder.forward.setOnClickListener {
         var   intent = Intent(context, RecipeActivity::class.java)

            intent.putExtra("img",dataList[position].img)
            intent.putExtra("tittle",dataList[position].tittle)
            intent.putExtra("des",dataList[position].des)
            intent.putExtra("ing",dataList[position].ing)
            intent.flags =Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}