package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_food_item_layout.view.*

class SearchFoodItemsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(allFoodsEntity: String) {
        view.apply {
            tvFoodNameAll.text = allFoodsEntity
//            tvNameAndWt.text = allFoodsEntity.quantity
//            tvCaloriesAll.text = allFoodsEntity.calories

        }
    }
}