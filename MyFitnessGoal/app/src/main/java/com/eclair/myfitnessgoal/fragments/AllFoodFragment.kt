package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_calories.*
import kotlinx.android.synthetic.main.activity_calories.view.*
import kotlinx.android.synthetic.main.fragment_all_food.*

class AllFoodFragment : Fragment() {

    private val searchItemList = mutableListOf<String>()

    private lateinit var database: FirebaseDatabase
    lateinit var searchFoodItemsAdapter: SearchFoodItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
        searchFoodItemsAdapter = SearchFoodItemsAdapter(searchItemList)

        rv_SearchFood.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_SearchFood.adapter = searchFoodItemsAdapter

        view.iBtnSearchFood.setOnClickListener {
            val searchedItem = view.etSearch.text.toString()

            getFoodItems(searchedItem)

        }


    }

    private fun getFoodItems(searchedItem: String) {
        database.reference.child(searchedItem).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    searchItemList.clear()

                    for (dataSnapshot in snapshot.children) {
                        val data = dataSnapshot.value.toString()
                        searchItemList.add(data)
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }

}