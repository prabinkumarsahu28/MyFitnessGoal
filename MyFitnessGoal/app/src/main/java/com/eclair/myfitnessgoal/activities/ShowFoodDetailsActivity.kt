@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import com.eclair.myfitnessgoal.roomdb.FoodViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import kotlinx.android.synthetic.main.activity_show_food_details.*


class ShowFoodDetailsActivity : AppCompatActivity() {

    lateinit var foodEntity: FoodEntity
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_food_details)

        if (intent != null && intent.extras != null) {
            foodEntity = intent.getSerializableExtra("foodItem") as FoodEntity
            type = intent.getStringExtra("type")
            tvFoodItemDisplay.text = foodEntity.foodName
        }

        val app = application as FoodApplication
        val repository = app.foodRepo
        val viewModelFactory = FoodViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)
        btnAddFoodItem.setOnClickListener {
            if (type == "save") {
                viewModel.addFood(foodEntity)
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("AddFood", 2)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

        }
    }

}