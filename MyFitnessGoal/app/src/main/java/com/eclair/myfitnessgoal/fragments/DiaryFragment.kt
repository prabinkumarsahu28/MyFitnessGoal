package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.CaloriesActivity
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import com.eclair.myfitnessgoal.roomdb.FoodViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : Fragment(), FoodClickListener {

    private val addFoodList = mutableListOf<FoodEntity>()
    lateinit var addFoodItemsAdapter: SearchFoodItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAddFood.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            startActivity(intent)

            tvAddLunchFood.setOnClickListener {
                val intent1 = Intent(context, CaloriesActivity::class.java)
                startActivity(intent1)

                tvAddDinnerFood.setOnClickListener {
                    val intent2 = Intent(context, CaloriesActivity::class.java)
                    startActivity(intent2)

                    tvAddSnackFood.setOnClickListener {
                        val intent3 = Intent(context, CaloriesActivity::class.java)
                        startActivity(intent3)
                    }
                }
            }
        }
        val app = activity?.application as FoodApplication
        val repository = app.foodRepo
        val viewModelFactory = FoodViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

        rv_breakFast.layoutManager = LinearLayoutManager(context)
        addFoodItemsAdapter = SearchFoodItemsAdapter(addFoodList, this)
        rv_breakFast.adapter = addFoodItemsAdapter

        rv_Lunch.layoutManager = LinearLayoutManager(context)
        addFoodItemsAdapter = SearchFoodItemsAdapter(addFoodList, this)
        rv_Lunch.adapter = addFoodItemsAdapter



        viewModel.getAllFood().observe(requireActivity(), Observer {
            addFoodList.clear()
            addFoodList.addAll(it)
            addFoodItemsAdapter.notifyDataSetChanged()

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false)

    }

    override fun onFoodItemClicked(foodEntity: FoodEntity) {

    }


}