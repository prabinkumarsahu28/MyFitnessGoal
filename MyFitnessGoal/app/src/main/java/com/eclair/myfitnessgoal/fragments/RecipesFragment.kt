package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.FarmersMarketAdapter
import com.eclair.myfitnessgoal.model.FarmersMarketClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesFragment : Fragment() {

    private val marketList = mutableListOf<FarmersMarketClass>()
    private lateinit var database : FirebaseDatabase
    lateinit var farmersMarketAdapter : FarmersMarketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
        farmersMarketAdapter = FarmersMarketAdapter(marketList)
        rv_farmersMarket.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rv_farmersMarket.adapter = farmersMarketAdapter

        getSprouts()

    }

    private fun getSprouts() {
        database.reference.child("market").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    marketList.clear()

                    for (dataSnapshot in snapshot.children){
                        val heading = dataSnapshot.child("")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}