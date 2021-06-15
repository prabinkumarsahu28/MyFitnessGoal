package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.fragment_goal.*


class GoalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgGoal.setOnCheckedChangeListener { radioGroup, p1 ->
            if (radioGroup.checkedRadioButtonId != -1) {
                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, ActivityLevelFragment())
                    .addToBackStack("activityLevelFragment").commit()
            }
        }
    }

}