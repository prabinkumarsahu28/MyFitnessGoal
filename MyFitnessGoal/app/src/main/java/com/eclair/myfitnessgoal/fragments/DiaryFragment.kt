package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eclair.myfitnessgoal.R
import com.eclair.views.CaloriesActivity
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAddFood.setOnClickListener {
            val intent = Intent(context,CaloriesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false)

    }
}