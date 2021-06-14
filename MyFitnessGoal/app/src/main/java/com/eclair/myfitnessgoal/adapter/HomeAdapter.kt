package com.eclair.myfitnessgoal.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eclair.myfitnessgoal.fragments.AllFoodFragment
import com.eclair.myfitnessgoal.fragments.MealsFragment
import com.eclair.myfitnessgoal.fragments.MyFoodFragment
import com.eclair.myfitnessgoal.fragments.RecipiesFragment

class HomeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return AllFoodFragment()
            }

            1-> {
                return RecipiesFragment()
            }

            2-> {
                return MealsFragment()
            }

            3-> {
                return MyFoodFragment()
            }else -> {
                return AllFoodFragment()
            }

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "ALL"
            }
            1 -> {
                return "RECIPES"
            }
            2-> {
                return "MEALS"
            }
            3 -> {
                return "MY FOODS"
            }
        }
        return super.getPageTitle(position)
    }
}