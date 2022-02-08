package com.example.foodapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityDetailOrderingBinding

class DetailOrderingActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailOrderingBinding
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrderingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper : DBHelper = DBHelper(this)

            val image = intent.getIntExtra("Image",0)
            val foodname = intent.getStringExtra("Name")
            val price = (intent.getStringExtra("Price"))
            val contentdescription = intent.getStringExtra("Description")

            binding.orderingImageView.setImageResource(image)
            binding.orderingFoodnameTextview.text = foodname
            binding.orderingPriceValueTextView.text = price
            binding.orderingDescriptionTextView.text = contentdescription

            binding.orderingOrdernowButton.setOnClickListener {

                    val isinserted: Boolean = helper.insertorder(
                        binding.orderingNameEditText.text.toString(),
                        binding.orderingPhoneEditText.text.toString(),
                        Integer.parseInt(binding.orderingNumberTextview.text.toString()),
                        price.toString(),
                        image,
                        contentdescription.toString(),
                        foodname.toString(),
                    )
                if (isinserted)
                    Toast.makeText(this, "Orders are Inserted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
    }
    fun decrement(view: View) {
        var count = Integer.parseInt(binding.orderingNumberTextview.text.toString())
        if (count > 1){
            count -= 1
            binding.orderingNumberTextview.text = count.toString()
        }
        else
            binding.orderingNumberTextview.text = "1"
    }
    fun increment(view: View) {
         var count = Integer.parseInt(binding.orderingNumberTextview.text.toString())
         count += 1
         binding.orderingNumberTextview.text = count.toString()
    }
}