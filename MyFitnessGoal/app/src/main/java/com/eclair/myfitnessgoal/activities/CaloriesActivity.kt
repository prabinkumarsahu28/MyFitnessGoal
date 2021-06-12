package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.eclair.myfitnessgoal.adapter.HomeAdapter
import com.eclair.myfitnessgoal.R
import com.google.android.material.tabs.TabLayout

class CaloriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)

        val viewPager = findViewById<ViewPager>(R.id.Pager)
        viewPager.adapter = HomeAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.HomeTabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }
}