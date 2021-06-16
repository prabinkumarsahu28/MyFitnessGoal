package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.models.Users
import kotlinx.android.synthetic.main.fragment_you.*

class YouFragment : Fragment() {

    private lateinit var user: Users

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = arguments?.getSerializable("user") as Users

        rgYou.setOnCheckedChangeListener { radioGroup, _ ->
            if (radioGroup.checkedRadioButtonId != -1) {

                var sex: String? = null

                when (radioGroup.checkedRadioButtonId) {
                    R.id.rbMale -> sex = "Male"
                    R.id.rbFemale -> sex = "Female"
                }

                val fragment = HeightWeightFragment()
                val bundle = Bundle()
                val users =
                    Users(user.userName,
                        user.email,
                        user.password,
                        user.goalType,
                        user.activeness,
                        sex)
                bundle.putSerializable("user", users)
                fragment.arguments = bundle

                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, fragment)
                    .addToBackStack("heightWeightFragment").commit()
            }
        }
    }
}