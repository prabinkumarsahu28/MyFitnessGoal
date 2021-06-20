package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.RecipesClass

class ViewAllRecipes : AppCompatActivity() {

    private val recipeList = mutableListOf<RecipesClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_recipes)
    }
}