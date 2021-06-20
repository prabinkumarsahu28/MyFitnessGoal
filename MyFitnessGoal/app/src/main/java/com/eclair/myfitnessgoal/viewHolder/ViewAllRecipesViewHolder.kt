package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.models.RecipesClass
import kotlinx.android.synthetic.main.market_item_layout.view.*

class ViewAllRecipesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(allRecipeClass: RecipesClass) {
        view.apply {
            tvMarketItemName.text = allRecipeClass.Name
            Glide.with(this).load(allRecipeClass.ImgLink).into(ivSproutsMarket)
            tvCal.text = allRecipeClass.cal
        }
    }
}