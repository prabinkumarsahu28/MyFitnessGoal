package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.models.RecipesClass

interface RecipeViewListener {
    fun onRecipeClicked(recipesClass: RecipesClass)
}