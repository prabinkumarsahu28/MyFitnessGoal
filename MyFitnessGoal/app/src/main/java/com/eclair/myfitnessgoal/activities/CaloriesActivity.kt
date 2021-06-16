package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_calories.*

class CaloriesActivity : AppCompatActivity(), FoodClickListener {


    private val searchItemList = mutableListOf<FoodEntity>()
    private lateinit var database: FirebaseDatabase
    lateinit var searchFoodItemsAdapter: SearchFoodItemsAdapter
    var foodType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)


        database = FirebaseDatabase.getInstance()
        searchFoodItemsAdapter = SearchFoodItemsAdapter(searchItemList, this)

        if (intent != null && intent.extras != null) {
            foodType = intent.getStringExtra("type").toString()
        }

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
                        val temp: List<String> = data.split(",")

                        val foodEntity = FoodEntity(temp[0], temp[1], temp[2],foodType!!)
                        searchItemList.add(foodEntity)

                    }

                    searchFoodItemsAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    override fun onFoodItemClicked(foodEntity: FoodEntity) {
        val intent = Intent(this, ShowFoodDetailsActivity::class.java)
        intent.putExtra("foodItem", foodEntity)
        startActivity(intent)

    }


}