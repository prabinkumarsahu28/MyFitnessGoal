package com.eclair.myfitnessgoal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.listeners.ExerciseClickListener
import com.eclair.myfitnessgoal.roomdb.ExerciseEntity
import com.eclair.myfitnessgoal.viewHolder.ExerciseViewHolder

class ExerciseAdapter(
    private val exercises: List<ExerciseEntity>,
    private val exerciseClickListener: ExerciseClickListener,
) : RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_food_item_layout, parent, false)

        return ExerciseViewHolder(view, exerciseClickListener)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.setData(exercises[position])
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}