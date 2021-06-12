package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.model.FarmersMarketClass
import kotlinx.android.synthetic.main.market_item_layout.view.*

class FarmersMarketViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(farmersMarketClass: FarmersMarketClass) {

        view.apply {
            Glide.with(this).load(farmersMarketClass.FoodImage).into(ivSproutsMarket)
            tvMarketItemName.text = farmersMarketClass.FoodName
            tvCal.text = farmersMarketClass.FoodCal
        }

    }
}