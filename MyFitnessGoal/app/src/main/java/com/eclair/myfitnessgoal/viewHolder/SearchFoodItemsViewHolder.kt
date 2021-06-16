package com.eclair.myfitnessgoal.viewHolder

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import kotlinx.android.synthetic.main.search_food_item_layout.view.*


class SearchFoodItemsViewHolder(
    private val view: View,

    private val foodClickListener: FoodClickListener,
) : RecyclerView.ViewHolder(view) {

    fun setData(allFoodsEntity: FoodEntity) {
        view.apply {
            tvFoodNameAll.text = allFoodsEntity.foodName
            tvNameAndWt.text = allFoodsEntity.quantity
            tvCaloriesAll.text = allFoodsEntity.calories

            clFoodItem.setOnClickListener {
                foodClickListener.onFoodItemClicked(allFoodsEntity, "detail")
            }

            clFoodItem.setOnLongClickListener {
                val builder = AlertDialog.Builder(context)

                builder.setMessage("Diary")
                builder.setPositiveButton("Delete Entry") { _, _ ->
                    builder.setIcon(R.drawable.ic_iconfinder_1564502_basket_delete_remove_icon)
                    foodClickListener.onFoodItemClicked(allFoodsEntity, "delete")
                }

                builder.setNegativeButton("No") { _, _ ->
                    Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
                }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                false
            }

        }
    }
}