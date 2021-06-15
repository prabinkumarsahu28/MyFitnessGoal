package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import kotlinx.android.synthetic.main.search_food_item_layout.view.*

class SearchFoodItemsViewHolder(private val view: View,private val foodClickListener: FoodClickListener) : RecyclerView.ViewHolder(view) {

    fun setData(allFoodsEntity: FoodEntity) {
        view.apply {
            tvFoodNameAll.text = allFoodsEntity.foodName
            tvNameAndWt.text = allFoodsEntity.quantity
            tvCaloriesAll.text = allFoodsEntity.calories

            tvFoodNameAll.setOnClickListener {
                foodClickListener.onFoodItemClicked(allFoodsEntity)
            }

        }
    }
}