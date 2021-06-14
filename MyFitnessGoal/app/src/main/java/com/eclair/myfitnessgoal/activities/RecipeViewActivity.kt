package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.activity_recipe_view.*

class RecipeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_view)

        if (intent != null && intent.extras != null) {
            val image = intent.getStringExtra("recipe")
            Glide.with(ivRecipeImageView).load(image).into(ivRecipeImageView)
        }
    }
}