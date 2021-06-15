package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.fragment_you.*

class YouFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgYou.setOnCheckedChangeListener { radioGroup, p1 ->
            if (radioGroup.checkedRadioButtonId != -1) {
                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, HeightWeightFragment())
                    .addToBackStack("heightWeightFragment").commit()
            }
        }
    }

}