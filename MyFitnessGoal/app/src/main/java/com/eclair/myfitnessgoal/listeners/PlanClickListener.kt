package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.models.Plans

interface PlanClickListener {
    fun onPlanClicked(plans: Plans)
}