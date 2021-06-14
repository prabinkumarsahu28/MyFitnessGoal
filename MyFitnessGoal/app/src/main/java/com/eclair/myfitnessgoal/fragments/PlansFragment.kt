package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.PlanDetailsActivity
import com.eclair.myfitnessgoal.adapter.PlansAdapter
import com.eclair.myfitnessgoal.listeners.PlanClickListener
import com.eclair.myfitnessgoal.models.Plans
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_plans.*

class PlansFragment : Fragment(), PlanClickListener {

    private val plansList = mutableListOf<Plans>()
    private lateinit var database: FirebaseDatabase
    lateinit var plansAdapter: PlansAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()
        plansAdapter = PlansAdapter(plansList, this)
        rvPlans.layoutManager = LinearLayoutManager(activity)
        rvPlans.adapter = plansAdapter

        getPlans()
    }

    private fun getPlans() {
        database.reference.child("Plans").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    plansList.clear()

                    for (dataSnapshot in snapshot.children) {
                        val difficulty = dataSnapshot.child("diff").value.toString()
                        val duration = dataSnapshot.child("dur").value.toString()
                        val img = dataSnapshot.child("img").value.toString()
                        val name = dataSnapshot.child("name").value.toString()
                        val perWeek = dataSnapshot.child("perWeek").value.toString()

                        val plan = Plans(difficulty, duration, img, name, perWeek)
                        plansList.add(plan)
                    }
                    plansAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onPlanClicked(plans: Plans) {
        val intent = Intent(activity, PlanDetailsActivity::class.java)
        intent.putExtra("plan", plans)
        startActivity(intent)
    }

}