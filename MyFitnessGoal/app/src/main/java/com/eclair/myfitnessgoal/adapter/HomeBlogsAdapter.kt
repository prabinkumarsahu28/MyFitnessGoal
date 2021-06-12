package com.eclair.myfitnessgoal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.listeners.BlogClickListener
import com.eclair.myfitnessgoal.models.HomeBlogs

class HomeBlogsAdapter(
    private val blogList: List<HomeBlogs>,
    private val blogClickListener: BlogClickListener
) : RecyclerView.Adapter<HomeBlogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBlogsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_sample_layout, parent, false)

        return HomeBlogsViewHolder(view, blogClickListener)
    }

    override fun onBindViewHolder(holder: HomeBlogsViewHolder, position: Int) {
        holder.setData(blogList[position])
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}