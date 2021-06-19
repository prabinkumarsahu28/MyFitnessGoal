package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FoodViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: FoodViewModel
    private val uid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val app = application as FoodApplication
        val repo = app.foodRepo
        val foodViewModelFactory = FoodViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            tvSoubiaEdit.text = viewModel.getUserName(uid)
            tvHeightValEdit.text = "${viewModel.getHeight(uid)} cm"
            tvEmailValEdit.text = viewModel.getUserEmail(uid)
            tvDobValEdit.text = viewModel.getDob(uid)
            tvSexValEdit.text = viewModel.getSex(uid)
            tvGoalsValEdit.text = "${viewModel.getGoal(uid)}, nutrition and fitnessGoals"

        }

    }
}