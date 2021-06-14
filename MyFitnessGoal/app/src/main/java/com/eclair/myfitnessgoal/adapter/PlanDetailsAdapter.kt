package com.eclair.myfitnessgoal.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eclair.myfitnessgoal.fragments.PlanOverviewFragment
import com.eclair.myfitnessgoal.fragments.PlanScheduleFragment
import com.eclair.myfitnessgoal.models.Plans

class PlanDetailsAdapter(fragmentManager: FragmentManager, val plans: Plans) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle()
                val plansOverviewFragment = PlanOverviewFragment()
                bundle.putSerializable("plans", plans)
                plansOverviewFragment.arguments = bundle
                plansOverviewFragment
            }
            1 -> PlanScheduleFragment()
            else -> PlanOverviewFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "OVERVIEW"
            }
            1 -> {
                return "SCHEDULE"
            }
        }
        return super.getPageTitle(position)
    }
}