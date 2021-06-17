@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.CaloriesActivity
import com.eclair.myfitnessgoal.activities.ShowFoodDetailsActivity
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FoodEntity
import com.eclair.myfitnessgoal.roomdb.FoodViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryFragment : Fragment(), FoodClickListener {

    private val breakFastList = mutableListOf<FoodEntity>()
    private val lunchList = mutableListOf<FoodEntity>()
    private val dinnerList = mutableListOf<FoodEntity>()
    private val snackList = mutableListOf<FoodEntity>()

    private lateinit var viewModel: FoodViewModel

    private lateinit var breakFastAdapter: SearchFoodItemsAdapter
    private lateinit var lunchAdapter: SearchFoodItemsAdapter
    private lateinit var dinnerAdapter: SearchFoodItemsAdapter
    private lateinit var snackAdapter: SearchFoodItemsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAddFood.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            intent.putExtra("type", "breakfast")
            startActivity(intent)
        }

        tvAddLunchFood.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            intent.putExtra("type", "lunch")
            startActivity(intent)
        }

        tvAddDinnerFood.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            intent.putExtra("type", "dinner")
            startActivity(intent)
        }

        tvAddSnackFood.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            intent.putExtra("type", "snack")
            startActivity(intent)
        }

        val app = activity?.application as FoodApplication
        val repository = app.foodRepo
        val viewModelFactory = FoodViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

        rv_breakFast.layoutManager = LinearLayoutManager(context)
        breakFastAdapter = SearchFoodItemsAdapter(breakFastList, this)
        rv_breakFast.adapter = breakFastAdapter

        rv_Lunch.layoutManager = LinearLayoutManager(context)
        lunchAdapter = SearchFoodItemsAdapter(lunchList, this)
        rv_Lunch.adapter = lunchAdapter

        rv_Dinner.layoutManager = LinearLayoutManager(context)
        dinnerAdapter = SearchFoodItemsAdapter(dinnerList, this)
        rv_Dinner.adapter = dinnerAdapter

        rv_Snacks.layoutManager = LinearLayoutManager(context)
        snackAdapter = SearchFoodItemsAdapter(snackList, this)
        rv_Snacks.adapter = snackAdapter



        viewModel.getALlAddedFood("breakfast").observe(requireActivity(), {
            breakFastList.clear()
            breakFastList.addAll(it)
            breakFastAdapter.notifyDataSetChanged()

        })

        viewModel.getALlAddedFood("lunch").observe(requireActivity(), {
            lunchList.clear()
            lunchList.addAll(it)
            lunchAdapter.notifyDataSetChanged()

        })

        viewModel.getALlAddedFood("dinner").observe(requireActivity(), {
            dinnerList.clear()
            dinnerList.addAll(it)
            dinnerAdapter.notifyDataSetChanged()

        })

        viewModel.getALlAddedFood("snack").observe(requireActivity(), {
            snackList.clear()
            snackList.addAll(it)
            snackAdapter.notifyDataSetChanged()

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false)

    }

    override fun onFoodItemClicked(foodEntity: FoodEntity, s: String) {

        if (s == "delete") {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getDeletedFoodItem(foodEntity)
            }
        } else {
            val intent = Intent(context, ShowFoodDetailsActivity::class.java)
            intent.putExtra("foodItem", foodEntity)
            intent.putExtra("type", "view")
            startActivity(intent)
        }

    }

}