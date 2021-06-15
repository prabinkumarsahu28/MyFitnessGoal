package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.eclair.myfitnessgoal.adapter.HomeAdapter
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_calories.*
import kotlinx.android.synthetic.main.activity_calories.view.*

class CaloriesActivity : AppCompatActivity() {


    private val searchItemList = mutableListOf<String>()
    private lateinit var database: FirebaseDatabase
    lateinit var searchFoodItemsAdapter: SearchFoodItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)

        database = FirebaseDatabase.getInstance()
        searchFoodItemsAdapter = SearchFoodItemsAdapter(searchItemList)

        rv_SearchFood.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_SearchFood.adapter = searchFoodItemsAdapter

        iBtnSearchFood.setOnClickListener {
            val searchedItem = etSearch.text.toString()

            getFoodItems(searchedItem)

            pb_SearchFood.visibility = View.VISIBLE

        }


    }

    private fun getFoodItems(searchedItem: String) {
        database.reference.child(searchedItem).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    searchItemList.clear()
                    pb_SearchFood.visibility = View.INVISIBLE

                    for (dataSnapshot in snapshot.children) {
                        val data = dataSnapshot.value.toString()
                        val temp : List<String> = data.split(",")

                        searchItemList.add(data)

                    }

                    searchFoodItemsAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }
}