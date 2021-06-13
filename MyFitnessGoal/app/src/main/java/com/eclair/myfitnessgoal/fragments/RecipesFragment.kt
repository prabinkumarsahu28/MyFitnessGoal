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
import com.eclair.myfitnessgoal.activities.RecipeViewActivity
import com.eclair.myfitnessgoal.adapter.FarmersMarketAdapter
import com.eclair.myfitnessgoal.listeners.RecipeViewListener
import com.eclair.myfitnessgoal.models.RecipesClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesFragment : Fragment(), RecipeViewListener {

    private val marketList = mutableListOf<RecipesClass>()
    private val highProteinList = mutableListOf<RecipesClass>()
    private val postWorkOutList = mutableListOf<RecipesClass>()
    private val preWorkOutList = mutableListOf<RecipesClass>()
    private val immuneSystemList = mutableListOf<RecipesClass>()

    private lateinit var database: FirebaseDatabase
    lateinit var farmersMarketAdapter: FarmersMarketAdapter
    lateinit var highProteinAdapter: FarmersMarketAdapter
    lateinit var postWorkOutAdapter: FarmersMarketAdapter
    lateinit var preWorkoutAdapter: FarmersMarketAdapter
    lateinit var immuneSupportAdapter: FarmersMarketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
        farmersMarketAdapter = FarmersMarketAdapter(marketList,this)
        highProteinAdapter = FarmersMarketAdapter(highProteinList,this)
        postWorkOutAdapter = FarmersMarketAdapter(postWorkOutList,this)
        preWorkoutAdapter = FarmersMarketAdapter(preWorkOutList,this)
        immuneSupportAdapter = FarmersMarketAdapter(immuneSystemList,this)


        rv_highProtein.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_highProtein.adapter = highProteinAdapter

        rv_immuneSupport.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_immuneSupport.adapter = immuneSupportAdapter

        rv_postWorkout.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_postWorkout.adapter = postWorkOutAdapter

        rv_preWorkout.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_preWorkout.adapter = preWorkoutAdapter

        rv_farmersMarket.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_farmersMarket.adapter = farmersMarketAdapter



        getRecipes()

    }

    private fun getRecipes() {
        database.reference.child("Recipes").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    marketList.clear()
                    highProteinList.clear()
                    postWorkOutList.clear()
                    preWorkOutList.clear()
                    immuneSystemList.clear()
                    var count = 0;

                    for (dataSnapshot in snapshot.children) {
                        for (valueSnapshot in dataSnapshot.children) {
                            val cal = valueSnapshot.child("Cal").value.toString()
                            val carbsPer = valueSnapshot.child("CarbsPer").value.toString()
                            val carbsWt = valueSnapshot.child("CarbsWt").value.toString()
                            val fatPer = valueSnapshot.child("FatPer").value.toString()
                            val fatWt = valueSnapshot.child("FatWt").value.toString()
                            val imgLink = valueSnapshot.child("ImgLink").value.toString()
                            val name = valueSnapshot.child("Name").value.toString()
                            val proteinPer = valueSnapshot.child("ProteinPer").value.toString()
                            val proteinWt = valueSnapshot.child("ProteinWt").value.toString()

                            val recipes = RecipesClass(
                                cal,
                                carbsPer,
                                carbsWt,
                                fatPer,
                                fatWt,
                                imgLink,
                                name,
                                proteinPer,
                                proteinWt
                            )
                            when {
                                count < 6 -> {
                                    highProteinList.add(recipes)
                                }
                                count in 7..12 -> {
                                    immuneSystemList.add(recipes)
                                }
                                count in 12..18 -> {
                                    postWorkOutList.add(recipes)
                                }
                                count in 18..24 -> {
                                    preWorkOutList.add(recipes)
                                }
                                count in 24..30 -> {
                                    marketList.add(recipes)
                                }
                            }
                            count++
                        }
                        farmersMarketAdapter.notifyDataSetChanged()
                        highProteinAdapter.notifyDataSetChanged()
                        postWorkOutAdapter.notifyDataSetChanged()
                        preWorkoutAdapter.notifyDataSetChanged()
                        immuneSupportAdapter.notifyDataSetChanged()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onRecipeClicked(recipesClass: RecipesClass) {
        val intent = Intent(context, RecipeViewActivity::class.java)
        intent.putExtra("recipes", recipesClass)
        startActivity(intent)
    }
}