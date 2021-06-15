package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import com.eclair.myfitnessgoal.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_all_food.*

class AllFoodFragment : Fragment() {

//    private val searchItemList = mutableListOf<AllFoodsEntity>()

    private lateinit var database: FirebaseDatabase
//    lateinit var searchFoodItemsAdapter: SearchFoodItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
//        searchFoodItemsAdapter = SearchFoodItemsAdapter(searchItemList)

        rv_SearchFood.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
//        rv_SearchFood.adapter = searchFoodItemsAdapter

        getFoodItems()
    }

    private fun getFoodItems() {
        database.reference.child("")
    }

}