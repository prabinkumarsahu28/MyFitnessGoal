@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.CaloriesActivity
import com.eclair.myfitnessgoal.activities.ShowFoodDetailsActivity
import com.eclair.myfitnessgoal.adapter.SearchFoodItemsAdapter
import com.eclair.myfitnessgoal.listeners.FoodClickListener
import com.eclair.myfitnessgoal.roomdb.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class DiaryFragment : Fragment(), FoodClickListener {

    private val breakFastList = mutableListOf<FoodEntity>()
    private val lunchList = mutableListOf<FoodEntity>()
    private val dinnerList = mutableListOf<FoodEntity>()
    private val snackList = mutableListOf<FoodEntity>()

    private lateinit var viewModel: FoodViewModel
    private lateinit var userViewModel: UserViewModel

    private lateinit var breakFastAdapter: SearchFoodItemsAdapter
    private lateinit var lunchAdapter: SearchFoodItemsAdapter
    private lateinit var dinnerAdapter: SearchFoodItemsAdapter
    private lateinit var snackAdapter: SearchFoodItemsAdapter
    private var reqDate: String? = null
    private var curDate: String? = null
    private var yesterday: String? = null
    private var tomorrow: String? = null
    private var req = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        curDate = dateFormat.format(Date())

        val formatType = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = formatType.parse(curDate!!)
        val calendar = Calendar.getInstance()
        calendar.time = date!!
        calendar.add(Calendar.DATE, -1)
        yesterday = formatType.format(calendar.time)

        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, +1)
        tomorrow = formatType.format(cal.time)

        val app = activity?.application as FoodApplication
        val repository = app.foodRepo
        val viewModelFactory = FoodViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

        val userRepo = app.userRepo
        val userViewModelFactory = UserViewModelFactory(userRepo)
        userViewModel =
            ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel::class.java)

        clickListeners()
        recyclerData()
        getData()

    }

    private fun getData() {

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.parse(curDate!!)
        val calendar = Calendar.getInstance()
        calendar.time = date!!
        calendar.add(Calendar.DATE, req)
        reqDate = dateFormat.format(calendar.time)

        when (reqDate) {
            curDate -> {
                tvDateDiary.text = "Today"
            }
            yesterday -> {
                tvDateDiary.text = "Yesterday"
            }
            tomorrow -> {
                tvDateDiary.text = "Tomorrow"
            }
            else -> {
                tvDateDiary.text = reqDate
            }
        }

        val uid = FirebaseAuth.getInstance().uid
        CoroutineScope(Dispatchers.IO).launch {
            tvGoalCal.text = userViewModel.getReqCalorie(uid!!)
        }

        viewModel.getTotalCalorie().observe(requireActivity(), {
            tvFoodCal.text = it.toString()
        })

        viewModel.getFoodDateWise(reqDate!!, "breakfast").observe(requireActivity(), {
            breakFastList.clear()
            breakFastList.addAll(it)
            breakFastAdapter.notifyDataSetChanged()

        })

        viewModel.getFoodDateWise(reqDate!!, "lunch").observe(requireActivity(), {
            lunchList.clear()
            lunchList.addAll(it)
            lunchAdapter.notifyDataSetChanged()

        })

        viewModel.getFoodDateWise(reqDate!!, "dinner").observe(requireActivity(), {
            dinnerList.clear()
            dinnerList.addAll(it)
            dinnerAdapter.notifyDataSetChanged()

        })

        viewModel.getFoodDateWise(reqDate!!, "snack").observe(requireActivity(), {
            snackList.clear()
            snackList.addAll(it)
            snackAdapter.notifyDataSetChanged()

        })
    }

    private fun recyclerData() {
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
    }

    private fun clickListeners() {
        ivPreviousDate.setOnClickListener {
            req--
            getData()
        }

        ivNextDate.setOnClickListener {
            req++
            getData()
        }

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

        tvAddExercise.setOnClickListener {
            val intent = Intent(context, CaloriesActivity::class.java)
            intent.putExtra("type", "exercise")
            startActivity(intent)
        }
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