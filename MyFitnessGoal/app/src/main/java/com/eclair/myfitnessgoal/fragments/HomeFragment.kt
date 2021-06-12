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
import com.eclair.myfitnessgoal.activities.BlogViewActivity
import com.eclair.myfitnessgoal.activities.VideoPlayerActivity
import com.eclair.myfitnessgoal.adapter.HomeBlogsAdapter
import com.eclair.myfitnessgoal.listeners.BlogClickListener
import com.eclair.myfitnessgoal.models.HomeBlogs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), BlogClickListener {

    private val blogsList = mutableListOf<HomeBlogs>()
    private lateinit var database: FirebaseDatabase
    lateinit var blogsAdapter: HomeBlogsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()
        blogsAdapter = HomeBlogsAdapter(blogsList, this)
        recyclerViewHome.layoutManager = LinearLayoutManager(activity)
        recyclerViewHome.adapter = blogsAdapter

        getBlogs()
    }

    private fun getBlogs() {
        database.reference.child("Home").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    blogsList.clear()

                    for (dataSnapshot in snapshot.children) {
                        val heading = dataSnapshot.child("heading").value.toString()
                        val img = dataSnapshot.child("img").value.toString()
                        val link = dataSnapshot.child("link").value.toString()
                        val type = dataSnapshot.child("type").value.toString()

                        val blog = HomeBlogs(heading, img, link, type)
                        blogsList.add(blog)
                    }
                    blogsAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBlogClicked(homeBlogs: HomeBlogs) {
        if (homeBlogs.type.equals("img")) {
            val intent = Intent(context, BlogViewActivity::class.java)
            intent.putExtra("link", homeBlogs.link)
            startActivity(intent)
        } else {
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("link", homeBlogs.link)
            startActivity(intent)
        }
    }
}