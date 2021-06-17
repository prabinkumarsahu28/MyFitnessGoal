package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userName: String? = null

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
                    supportActionBar?.title = "MyFitnessGoal"
                    currentFragment(homeFragment)
                }
                R.id.ic_diary -> {
                    supportActionBar?.title = "Diary"
                    currentFragment(diaryFragment)
                }
                R.id.ic_recipes -> {
                    supportActionBar?.title = "Recipes"
                    currentFragment(recipesFragment)
                }
                R.id.ic_plans -> {
                    supportActionBar?.title = "Plans"
                    currentFragment(plansFragment)
                }
                R.id.ic_me -> {
                    supportActionBar?.title = "About"
                    currentFragment(meFragment)
                }

            }
            true
        }

        if (intent != null && intent.extras != null) {
            if (intent.getIntExtra("AddFood", 0) == 2)
                currentFragment(diaryFragment)
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
        val bundle = Bundle()
        if (intent != null && intent.extras != null) {
            userName = intent.getStringExtra("userName")

        }
        bundle.putString("userName", userName)

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.flContainer, fragment)
            commit()
        }
    }
}