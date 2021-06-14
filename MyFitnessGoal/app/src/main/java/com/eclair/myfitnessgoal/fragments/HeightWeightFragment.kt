package com.eclair.myfitnessgoal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.fragment_height_weight.*

class HeightWeightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_height_weight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextHeightWt.setOnClickListener {
            if (checkValidation()) {
                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.flSignUp, DetailsFragment())
                    .addToBackStack("detailsFragment").commit()
            }
        }

    }

    private fun checkValidation(): Boolean {
        if (etHeight.text.isEmpty()) {
            etHeight.error = "Must fill"
            return false
        }
        if (etWeight.text.isEmpty()) {
            etWeight.error = "Must fill"
            return false
        }
        return true
    }

}