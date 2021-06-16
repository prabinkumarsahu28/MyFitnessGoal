package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.Users
import kotlinx.android.synthetic.main.fragment_activity_level.*

class ActivityLevelFragment : Fragment() {

    private lateinit var user: Users

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = arguments?.getSerializable("user") as Users

        rgActivity.setOnCheckedChangeListener { radioGroup, _ ->
            if (radioGroup.checkedRadioButtonId != -1) {

                var activeness: String? = null

                when (radioGroup.checkedRadioButtonId) {
                    R.id.rbNotVeryActive -> activeness = "Not Very Active"
                    R.id.rbLightlyActive -> activeness = "Lightly Active"
                    R.id.rbActive -> activeness = "Active"
                    R.id.rbVeryActive -> activeness = "Very Active"
                }

                val fragment = YouFragment()
                val bundle = Bundle()
                val users =
                    Users(user.userName, user.email, user.password, user.goalType, activeness)
                bundle.putSerializable("user", users)
                fragment.arguments = bundle
                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, fragment)
                    .addToBackStack("youFragment").commit()
            }
        }
    }

}