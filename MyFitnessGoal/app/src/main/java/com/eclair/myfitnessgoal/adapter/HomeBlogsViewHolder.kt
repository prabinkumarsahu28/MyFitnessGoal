package com.eclair.myfitnessgoal.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.listeners.BlogClickListener
import com.eclair.myfitnessgoal.models.HomeBlogs
import kotlinx.android.synthetic.main.home_sample_layout.view.*

class HomeBlogsViewHolder(view: View, private val blogClickListener: BlogClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setData(homeBlogs: HomeBlogs) {

        itemView.apply {
            if (homeBlogs.type.equals("vid")) {
                ivVdoPlayButtonHome.visibility = View.VISIBLE
            }
            Glide.with(this).load(homeBlogs.img).into(ivImageHome)
            tvHeadingHome.text = homeBlogs.heading

            ivImageHome.setOnClickListener {
                blogClickListener.onBlogClicked(homeBlogs)
            }
        }

    }

}