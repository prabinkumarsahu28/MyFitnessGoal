package com.eclair.myfitnessgoal.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_calories.*
import java.text.SimpleDateFormat
import java.util.*

class CaloriesActivity : AppCompatActivity(), FoodClickListener {

    private val searchItemList = mutableListOf<FoodEntity>()
    private lateinit var database: FirebaseDatabase
    lateinit var searchFoodItemsAdapter: SearchFoodItemsAdapter
    var type: String? = null
    var curDate: String? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)


        database = FirebaseDatabase.getInstance()
        searchFoodItemsAdapter = SearchFoodItemsAdapter(searchItemList, this)

        if (intent != null && intent.extras != null) {
            type = intent.getStringExtra("type")
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        curDate = dateFormat.format(Date())

        rv_SearchFood.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_SearchFood.adapter = searchFoodItemsAdapter

        iBtnSearchFood.setOnClickListener {
            if (etSearch.text.toString().isNotEmpty()) {
                val searchedItem = etSearch.text.toString()
                getFoodItems(searchedItem)
                pb_SearchFood.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "fill something", Toast.LENGTH_SHORT).show()
            }
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

                        val foodEntity = FoodEntity(temp[0], temp[1], temp[2], type!!,
                            curDate!!, FirebaseAuth.getInstance().uid!!)
                        searchItemList.add(foodEntity)

                    }

                    searchFoodItemsAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                pb_SearchFood.visibility = View.INVISIBLE
                Toast.makeText(this@CaloriesActivity, "Not found", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onFoodItemClicked(foodEntity: FoodEntity, s: String) {
        val intent = Intent(this, ShowFoodDetailsActivity::class.java)
        intent.putExtra("foodItem", foodEntity)
        intent.putExtra("type", "save")
        startActivity(intent)

    }

}