package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.listeners.ExerciseClickListener
import com.eclair.myfitnessgoal.roomdb.ExerciseEntity
import kotlinx.android.synthetic.main.search_food_item_layout.view.*

class ExerciseViewHolder(private  val view: View, private val exerciseClickListener: ExerciseClickListener) :
    RecyclerView.ViewHolder(view) {

        fun setData(exerciseEntity: ExerciseEntity){
            view.apply {
                tvFoodNameAll.text = exerciseEntity.name
                tvCaloriesAll.text = "${exerciseEntity.cal} cal/min"

                clFoodItem.setOnClickListener {
                    exerciseClickListener.onClicked(exerciseEntity)
                }
            }
        }
}