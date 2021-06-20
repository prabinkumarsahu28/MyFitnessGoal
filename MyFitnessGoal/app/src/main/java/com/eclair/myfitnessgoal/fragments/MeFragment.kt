@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.AddWeightActivity
import com.eclair.myfitnessgoal.activities.SettingsActivity
import com.eclair.myfitnessgoal.activities.UpdateGoalsActivity
import com.eclair.myfitnessgoal.roomdb.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {

    private lateinit var viewModel: FitnessViewModel
    private val uid = FirebaseAuth.getInstance().uid
    private var weight = 0
    lateinit var userEntity: UserEntity


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

        val app = activity?.application as FoodApplication
        val repo = app.foodRepo
        val foodViewModelFactory = FoodViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, foodViewModelFactory).get(FitnessViewModel::class.java)


        viewModel.getUserDetails(uid).observe(requireActivity(),{
            userEntity = it

            tvWeightKg.text = it.weight
            tvUidUser.text = it.userName
            tvUserEmail.text = it.emailId
            if (it.profilePic != "profilePic"){
              Glide.with(requireActivity()).load(it.profilePic.toUri()).placeholder(R.drawable.user).into(profile_image)
            }
            tvCalorieMe.text = it.reqCalorie
        })

        if (arguments != null) {
            weight = arguments?.getInt("weight", 0)!!
        }
        pb_SemiCircle.setPercentWithAnimation(weight)

        clickListeners()
    }


    private fun clickListeners() {
        iBtnSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

        tvUpdateGoals.setOnClickListener {
            val intent = Intent(context, UpdateGoalsActivity::class.java)
            startActivity(intent)
        }

        tvAddWeight.setOnClickListener {
            startActivity(Intent(context, AddWeightActivity::class.java))
        }
    }
}