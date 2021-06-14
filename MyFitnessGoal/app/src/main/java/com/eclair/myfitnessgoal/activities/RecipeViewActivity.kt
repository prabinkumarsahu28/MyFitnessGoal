package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.RecipesClass
import kotlinx.android.synthetic.main.activity_recipe_view.*

class RecipeViewActivity : AppCompatActivity() {

    private lateinit var recipe : RecipesClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_view)

        if (intent != null && intent.extras != null) {
            recipe = intent.getSerializableExtra("recipes") as RecipesClass
            Glide.with(this).load(recipe.ImgLink).into(ivRecipeImageView)
            tvRecipeName.text = recipe.Name
        }
    }
}