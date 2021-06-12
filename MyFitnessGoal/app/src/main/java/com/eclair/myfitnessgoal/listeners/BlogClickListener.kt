package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.models.HomeBlogs

interface BlogClickListener {
    fun onBlogClicked(homeBlogs: HomeBlogs)
}