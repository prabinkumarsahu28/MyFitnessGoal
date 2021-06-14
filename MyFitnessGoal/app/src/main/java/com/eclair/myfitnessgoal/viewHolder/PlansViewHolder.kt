package com.eclair.myfitnessgoal.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.listeners.PlanClickListener
import com.eclair.myfitnessgoal.models.Plans
import kotlinx.android.synthetic.main.plans_sample_layout.view.*

class PlansViewHolder(view: View, val planClickListener: PlanClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setData(plans: Plans) {

        itemView.apply {
            Glide.with(this).load(plans.img).into(ivPlans)
            tvPlansName.text = plans.name
            tvType.text = "${plans.duration}, ${plans.perWeek}"

            cvPlan.setOnClickListener {
                planClickListener.onPlanClicked(plans)
            }
        }

    }

}