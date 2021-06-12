package com.eclair.myfitnessgoal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.model.FarmersMarketClass
import com.eclair.myfitnessgoal.viewHolder.FarmersMarketViewHolder

class FarmersMarketAdapter(private val marketList: List<FarmersMarketClass>) :
    RecyclerView.Adapter<FarmersMarketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmersMarketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.market_item_layout, parent, false)
        return FarmersMarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmersMarketViewHolder, position: Int) {
        val farmersMarketClass = marketList[position]
        holder.setData(farmersMarketClass)
    }

    override fun getItemCount(): Int {
        return marketList.size
    }

}