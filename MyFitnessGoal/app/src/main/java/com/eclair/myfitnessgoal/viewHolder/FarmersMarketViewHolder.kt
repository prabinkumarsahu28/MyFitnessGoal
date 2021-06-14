package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.listeners.RecipeViewListener
import com.eclair.myfitnessgoal.models.FarmersMarketClass
import com.eclair.myfitnessgoal.models.RecipesClass
import kotlinx.android.synthetic.main.market_item_layout.view.*

class FarmersMarketViewHolder(private val view: View,private val recipeViewListener: RecipeViewListener) : RecyclerView.ViewHolder(view) {
    fun setData(recipesClass: RecipesClass) {

        view.apply {
            Glide.with(this).load(recipesClass.ImgLink).into(ivSproutsMarket)
            tvMarketItemName.text = recipesClass.Name
            tvCal.text = recipesClass.cal

            ivSproutsMarket.setOnClickListener {
                recipeViewListener.onRecipeClicked(recipesClass)
            }
        }
    }
}