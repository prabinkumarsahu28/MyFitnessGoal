package com.eclair.myfitnessgoal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.listeners.PlanClickListener
import com.eclair.myfitnessgoal.models.Plans
import com.eclair.myfitnessgoal.viewHolder.PlansViewHolder

class PlansAdapter(
    private val plansList: List<Plans>,
    private val planClickListener: PlanClickListener
) : RecyclerView.Adapter<PlansViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlansViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.plans_sample_layout, parent, false)
        return PlansViewHolder(view, planClickListener)
    }

    override fun onBindViewHolder(holder: PlansViewHolder, position: Int) {
        holder.setData(plansList[position])
    }

    override fun getItemCount(): Int {
        return plansList.size
    }
}