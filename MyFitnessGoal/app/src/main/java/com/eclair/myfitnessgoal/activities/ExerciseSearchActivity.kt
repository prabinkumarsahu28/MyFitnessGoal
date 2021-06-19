package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.ExerciseAdapter
import com.eclair.myfitnessgoal.listeners.ExerciseClickListener
import com.eclair.myfitnessgoal.roomdb.ExerciseEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_calories.*
import kotlinx.android.synthetic.main.activity_exercise_search.*
import java.text.SimpleDateFormat
import java.util.*

class ExerciseSearchActivity : AppCompatActivity(), ExerciseClickListener {

    private val exercises = mutableListOf<ExerciseEntity>()
    private lateinit var database: FirebaseDatabase
    private lateinit var exerciseAdapter: ExerciseAdapter
    var curDate: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_search)

        Log.d("prabin", "ExerciseSearch")

        database = FirebaseDatabase.getInstance()
        exerciseAdapter = ExerciseAdapter(exercises, this)
        rvSearchExercise.layoutManager = LinearLayoutManager(this)
        rvSearchExercise.adapter = exerciseAdapter

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        curDate = dateFormat.format(Date())

        clickListeners()
    }

    private fun clickListeners() {
        iBtnSearchExercise.setOnClickListener {
            if (etSearchExercise.text.toString().isNotEmpty()) {
                val searchedItem = etSearchExercise.text.toString()
                getExercises(searchedItem)
                pbSearchExercise.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "fill something", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getExercises(searchedItem: String) {
        database.reference.child(searchedItem).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    exercises.clear()
                    pbSearchExercise.visibility = View.INVISIBLE

                    for (dataSnapshot in snapshot.children) {
                        val data = dataSnapshot.value.toString()
                        val temp: List<String> = data.split(",")

                        val exerciseEntity =
                            ExerciseEntity(temp[0], temp[1],curDate, FirebaseAuth.getInstance().uid!!)
                        exercises.add(exerciseEntity)

                    }

                    exerciseAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                pbSearchExercise.visibility = View.INVISIBLE
                Toast.makeText(this@ExerciseSearchActivity, "Not found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClicked(exerciseEntity: ExerciseEntity) {
        val intent = Intent(this, ExerciseDetailsActivity::class.java)
        intent.putExtra("exercise", exerciseEntity)
        startActivity(intent)
    }
}