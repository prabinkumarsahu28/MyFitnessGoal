@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.roomdb.ExerciseEntity
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FoodViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import kotlinx.android.synthetic.main.activity_exercise_details.*
import java.text.SimpleDateFormat
import java.util.*


class ExerciseDetailsActivity : AppCompatActivity() {

    private lateinit var exerciseEntity1: ExerciseEntity
    private lateinit var exerciseEntity2: ExerciseEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_details)

        if (intent != null && intent.extras != null) {
            exerciseEntity1 = intent.getSerializableExtra("exercise") as ExerciseEntity
        }

        val app = application as FoodApplication
        val repository = app.foodRepo
        val viewModelFactory = FoodViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

        btnSaveExercise.setOnClickListener {

            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val curDate = dateFormat.format(Date())
            val cal = (etTimePerformed.text.toString().toInt() * 3)
            Log.d("prabin", "$cal")
            exerciseEntity2 =
                ExerciseEntity(exerciseEntity1.name, cal.toString(), curDate, exerciseEntity1.uid)

            viewModel.addExercise(exerciseEntity2)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("AddFood", 2)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            Toast.makeText(this,
                "Congratulations you've burned '$cal calories'",
                Toast.LENGTH_SHORT)
                .show()
        }
    }
}