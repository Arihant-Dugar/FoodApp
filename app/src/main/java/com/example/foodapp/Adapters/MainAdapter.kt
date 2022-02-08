package com.example.foodapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.DetailOrderingActivity
import com.example.foodapp.Models.MainModel
import com.example.foodapp.R

class MainAdapter(private val context: Context,private var items_list: List<MainModel>) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

//    private lateinit var mListener : onItemClickListener
//
//    interface onItemClickListener{
//
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: onItemClickListener){
//        mListener = listener
//    }


    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val name: TextView = itemView.findViewById(R.id.textView2)
        val price: TextView = itemView.findViewById(R.id.textView4)
        val description: TextView = itemView.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_to_show,parent,false)
        return MainHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val current_food_item = items_list[position]
        holder.imageView.setImageResource(current_food_item.image)
        holder.name.text = current_food_item.name
        holder.price.text = current_food_item.price
        holder.description.text = current_food_item.description

        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailOrderingActivity::class.java)
            intent.putExtra("Image",current_food_item.image)
            intent.putExtra("Name",current_food_item.name)
            intent.putExtra("Price",current_food_item.price)
            intent.putExtra("Description",current_food_item.description)
//            intent.putExtra("type",1)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return items_list.size
    }
}