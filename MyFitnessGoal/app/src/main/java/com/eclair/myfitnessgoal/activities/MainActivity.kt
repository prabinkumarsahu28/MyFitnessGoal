package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.R
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
                R.id.ic_home -> {
                    tvToolText.text = "MyFitnessGoal"
                    currentFragment(homeFragment)
                }
                R.id.ic_diary -> {
                    tvToolText.text = "Diary"
                    currentFragment(diaryFragment)
                }
                R.id.ic_recipes -> {
                    tvToolText.text = "Recipes"
                    currentFragment(recipesFragment)
                }
                R.id.ic_plans -> {
                    tvToolText.text = "Plans"
                    currentFragment(plansFragment)
                }
                R.id.ic_me -> {
                    tvToolText.text = "Profile"
                    currentFragment(meFragment)
                }

            }
            true
        }

        if (intent != null && intent.extras != null) {

            if (intent.getIntExtra("AddFood", 0) == 2) {
                bottomNavigation.selectedItemId = R.id.ic_diary

                currentFragment(diaryFragment)
            }
        }
    }

    override fun onBackPressed() {
        if (bottomNavigation.selectedItemId == R.id.ic_home) {
            super.onBackPressed()
            finish()
        } else {
            bottomNavigation.selectedItemId = R.id.ic_home
        }
    }

    private fun currentFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContainer, fragment).commit()
    }
}