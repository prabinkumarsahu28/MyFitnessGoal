package com.eclair.myfitnessgoal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val diaryFragment = DiaryFragment()
        val recipesFragment = RecipesFragment()
        val plansFragment = PlansFragment()
        val meFragment = MeFragment()

        currentFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> currentFragment(homeFragment)
                R.id.ic_diary -> currentFragment(diaryFragment)
                R.id.ic_recipes -> currentFragment(recipesFragment)
                R.id.ic_plans -> currentFragment(plansFragment)
                R.id.ic_me -> currentFragment(meFragment)

            }
            true
        }
    }

    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flContainer, fragment)
            commit()
        }
    }
}