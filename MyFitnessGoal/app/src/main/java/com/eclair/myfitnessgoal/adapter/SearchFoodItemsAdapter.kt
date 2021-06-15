package com.eclair.myfitnessgoal.adapter
//
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.viewHolder.SearchFoodItemsViewHolder

class SearchFoodItemsAdapter(private val searchItemList: MutableList<String>) :
    RecyclerView.Adapter<SearchFoodItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_food_item_layout, parent, false)
        return SearchFoodItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFoodItemsViewHolder, position: Int) {
        val allFoodsEntity = searchItemList[position]
        holder.setData(allFoodsEntity)
    }

    override fun getItemCount(): Int {
        return searchItemList.size
    }
}