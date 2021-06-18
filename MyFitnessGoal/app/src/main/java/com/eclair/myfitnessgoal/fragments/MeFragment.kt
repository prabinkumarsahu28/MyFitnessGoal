@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.SettingsActivity
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.UserViewModel
import com.eclair.myfitnessgoal.roomdb.UserViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private val uid = FirebaseAuth.getInstance().uid



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iBtnSettings.setOnClickListener {
            val intent = Intent(context,SettingsActivity::class.java)
            startActivity(intent)
        }

        val app = activity?.application as FoodApplication
        val repo = app.userRepo
        val userViewModelFactory = UserViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            tvCalorieMe.text = "${viewModel.getReqCalorie(uid)} cal"
            tvWeightKg.text = "${viewModel.getWeight(uid)} kg"
        }
    }
}