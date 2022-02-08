package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adapters.MainAdapter
import com.example.foodapp.Models.MainModel
import com.example.foodapp.databinding.ActivitySearchFoodBinding

class SearchFood : AppCompatActivity() {
    lateinit var binding: ActivitySearchFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val new_list = food_list(40)
        val adapter = MainAdapter(this@SearchFood,new_list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

//        adapter.setOnItemClickListener(object : MainAdapter.onItemClickListener{
//            override fun onItemClick(position: Int) {
//
//                val intent = Intent(this@SearchFood,DetailOrderingActivity::class.java)
//                intent.putExtra("image",new_list[position].image)
//                intent.putExtra("name",new_list[position].name)
//                intent.putExtra("price",new_list[position].price)
//                intent.putExtra("description",new_list[position].description)
//                startActivity(intent)
//         }
//   })
    }
    private fun food_list(size: Int) : List<MainModel>{

        val list = ArrayList<MainModel>()
        for (i in 1..size){
            val drawable = when(i % 4){
                1 -> list.add(MainModel(R.drawable.dessert,"DESSERT","2","This is very delightful dessert and u will love the taste of it."))
                2 -> list.add(MainModel(R.drawable.cutlet,"CUTLET","5","This is very delightful cutlet and u will love the taste of it."))
                3 -> list.add(MainModel(R.drawable.pizza,"PIZZA","10","This is very delightful pizza and u will love the taste of it."))
                else -> list.add(MainModel(R.drawable.dumplings,"DUMPLINGS","7","This is very delightful dumplings and u will love the taste of it."))
            }
        }
        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.orders_cart -> startActivity(Intent(this,OrderActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}