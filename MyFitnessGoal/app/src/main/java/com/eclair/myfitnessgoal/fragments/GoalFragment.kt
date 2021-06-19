package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.Users
import kotlinx.android.synthetic.main.fragment_goal.*


class GoalFragment : Fragment() {

    private lateinit var user: Users

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = if (arguments != null) {
            arguments?.getSerializable("user") as Users
        }else{
            Users()
        }


        clickListener()

    }

    private fun clickListener() {

        var goalType: String? = null

        rgGoal.setOnCheckedChangeListener { radioGroup, _ ->
            if (radioGroup.checkedRadioButtonId != -1) {
                when (radioGroup.checkedRadioButtonId) {
                    R.id.rbLoseWtGoal -> goalType = "Loose"
                    R.id.rbMaintainWtGoal -> goalType = "Maintain"
                    R.id.rbGainWtGoal -> goalType = "Gain"
                }

                val fragment = ActivityLevelFragment()
                val bundle = Bundle()
                val users = Users(user.userName, user.email, user.password, goalType, "less")
                bundle.putSerializable("user", users)
                fragment.arguments = bundle

                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, fragment)
                    .addToBackStack("activityLevelFragment").commit()
            }
        }
    }

}