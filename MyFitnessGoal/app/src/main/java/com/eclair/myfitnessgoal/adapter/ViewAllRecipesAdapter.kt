package com.eclair.myfitnessgoal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.RecipesClass
import com.eclair.myfitnessgoal.viewHolder.ViewAllRecipesViewHolder

class ViewAllRecipesAdapter(private val allRecipeList : MutableList<RecipesClass>) : RecyclerView.Adapter<ViewAllRecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllRecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_item_layout,parent,false)
        return ViewAllRecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewAllRecipesViewHolder, position: Int) {
        holder.setData(allRecipeList[position])
    }

    override fun getItemCount(): Int {
        return allRecipeList.size
    }
}