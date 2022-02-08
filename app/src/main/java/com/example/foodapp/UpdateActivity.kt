package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.foodapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = DBHelper(this)

        val foodname = intent.getStringExtra("foodname")
        val price = intent.getStringExtra("price")
        val image = intent.getIntExtra("image",0)
        val description = intent.getStringExtra("description")

        binding.updateImageView.setImageResource(image)
        binding.updatePriceValueTextView.text = price
        binding.updateFoodnameTextview.text = foodname
        binding.updateDescriptionTextView.text = description

        binding.updateOrdernowButton.setOnClickListener {
                val isupdated: Long = helper.updateorder(
                    binding.updateNameEditText.text.toString(),
                    binding.updatePhoneEditText.text.toString(),
                    Integer.parseInt(binding.updateNumberTextview.text.toString()),
                    binding.updatePriceValueTextView.text.toString(),
                    image,
                    binding.updateDescriptionTextView.text.toString(),
                    binding.updateFoodnameTextview.text.toString(),
                    intent.getIntExtra("ID_COL",0)
                )
                if (isupdated > -1)
                    Toast.makeText(this, "Order Updated", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    fun decrement_update(view: View) {
        var count = Integer.parseInt(binding.updateNumberTextview.text.toString())
        if (count > 1){
            count -= 1
            binding.updateNumberTextview.text = count.toString()
        }
        else
            binding.updateNumberTextview.text = "1"
    }
    fun increment_update(view: View) {
        var count = Integer.parseInt(binding.updateNumberTextview.text.toString())
        count += 1
        binding.updateNumberTextview.text = count.toString()
    }
}