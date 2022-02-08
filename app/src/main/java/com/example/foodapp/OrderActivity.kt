package com.example.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adapters.OrderAdapter
import com.example.foodapp.Models.OrdersModel
import com.example.foodapp.databinding.ActivityOrderBinding
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback as SimpleCallback1

class OrderActivity : AppCompatActivity() {
    lateinit var  binding : ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val helper = DBHelper(this)
        val new_order_list : ArrayList<OrdersModel> = helper.getorders()

        binding.orderRecyclerView.adapter = OrderAdapter(this,new_order_list)
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.orderRecyclerView.setHasFixedSize(true)

    }
}