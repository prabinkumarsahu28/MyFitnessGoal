package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.UserViewModel
import com.eclair.myfitnessgoal.roomdb.UserViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_goals.*
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateGoalsActivity : AppCompatActivity() {

    private var currentWeight :Int = 0
    private var goalWeight : Int = 0


    private lateinit var viewModel: UserViewModel
    private val uid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_goals)



        btnSaveDetails.setOnClickListener {

            currentWeight = etGoalWeightVal.text.toString().toInt()
            goalWeight = etGoalWeightVal.text.toString().toInt()

            if (currentWeight <= goalWeight){
                pb_SemiCircle.setPercent(goalWeight - currentWeight)
            }

            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("progressBar",4)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        val app = application as FoodApplication
        val repo = app.userRepo
        val userViewModelFactory = UserViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            tvWeightValGoals.text = "${viewModel.getWeight(uid)} kg"
        }
    }
}