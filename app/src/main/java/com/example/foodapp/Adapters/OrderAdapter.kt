package com.example.foodapp.Adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.DBHelper
import com.example.foodapp.Models.OrdersModel
import com.example.foodapp.R
import com.example.foodapp.UpdateActivity

class OrderAdapter(private val context: Context, private val orderlist : List<OrdersModel>) : RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    class OrderHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val order_imageView : ImageView = itemView.findViewById(R.id.order_image_view)
        val order_food_name : TextView = itemView.findViewById(R.id.order_textView2)
        val order_price : TextView = itemView.findViewById(R.id.order_textView5)
        val order_number : TextView = itemView.findViewById(R.id.order_textView6)
        val order_description : TextView = itemView.findViewById(R.id.order_textView9)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_data,parent,false)
        return OrderHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val ordercurrentposition = orderlist[position]
        holder.order_imageView.setImageResource(ordercurrentposition.order_image)
        holder.order_food_name.text = ordercurrentposition.order_foodname_name
        holder.order_price.text = ordercurrentposition.order_price
        holder.order_number.text = ordercurrentposition.id.toString()
        holder.order_description.text = ordercurrentposition.order_description

        holder.itemView.setOnClickListener {
            val intent = Intent(context,UpdateActivity::class.java)
            intent.putExtra("foodname",ordercurrentposition.order_foodname_name)
            intent.putExtra("price",ordercurrentposition.order_price)
            intent.putExtra("image",ordercurrentposition.order_image)
            intent.putExtra("description",ordercurrentposition.order_description)
            intent.putExtra("ID_COL",ordercurrentposition.id)
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Item")
                .setMessage("Are You Sure You Want To Delete This Order of ${ordercurrentposition.order_foodname_name}")
                .setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        val helper = DBHelper(context)
                        val delete = helper.deleteorder(ordercurrentposition.id)
                        if (delete > 0)
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
                .show()
               true
        }
//        holder.itemView.setOnClickListener {
//            val intent = Intent(context,DetailOrderingActivity::class.java)
//            intent.putExtra("id", Integer.parseInt(ordercurrentposition.order_no))
//            intent.putExtra("type", 2)
//            context.startActivity(intent)
//        }
    }
    override fun getItemCount(): Int {
       return orderlist.size
    }
}

