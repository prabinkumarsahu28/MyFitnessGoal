package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.activity_blog_view.*

class BlogViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_view)
        supportActionBar?.hide()

        if (intent != null && intent.extras != null) {
            val link = intent.getStringExtra("link").toString()
            wvBlog.loadUrl(link)
        }
    }
}