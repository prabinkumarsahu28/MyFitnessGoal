@file:Suppress("DEPRECATION")

package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.MainActivity
import com.eclair.myfitnessgoal.models.Users
import com.eclair.myfitnessgoal.roomdb.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_height_weight.*
import java.util.*
import kotlin.math.roundToInt

class HeightWeightFragment : Fragment() {

    private lateinit var user: Users
    private var age: String? = null
    private var dob: String? = null
    private var reqCalorie: Int = 0
    private var height: Int = 0
    private var weight: Int = 0

    private lateinit var viewModel: FoodViewModel
    private lateinit var userEntity: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_height_weight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        user = arguments?.getSerializable("user") as Users

        clickListener()

        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { _, year, month, day ->
            age = getAge(year, month, day)
            dob = "$day/${month + 1}/$year"
        }

    }

    private fun clickListener() {

        btnNextHeightWt.setOnClickListener {

            if (checkValidation()) {

                getCalorie()
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)

                val app = activity?.application as FoodApplication
                val repository = app.foodRepo
                val viewModelFactory = FoodViewModelFactory(repository)
                viewModel =
                    ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

                userEntity = UserEntity(user.userName!!, user.email!!,
                    FirebaseAuth.getInstance().uid!!,
                    user.password!!,
                    user.goalType!!,
                    user.activeness!!,
                    user.sex!!,
                    height.toString(),
                    weight.toString(),
                    dob!!,
                    reqCalorie.toString(),
                    "profilePic")
                viewModel.addData(userEntity)
            }
        }
    }

    private fun getCalorie() {
        height = etHeight.text.toString().toInt()
        weight = etWeight.text.toString().toInt()

        val beforeActiveness = if (user.sex == "Female") {
            ((weight * 2.20462 * 4.35) +
                    (height * 0.393701 * 4.7) - (age!!.toInt() * 4.7) + 655)
        } else {
            ((weight * 2.20462 * 6.23) +
                    (height * 0.393701 * 12.7) - (age!!.toInt() * 6.8) + 66)
        }

        when (user.activeness) {
            "Not Very Active" -> reqCalorie = (beforeActiveness * 1.2).roundToInt()
            "Lightly Active" -> reqCalorie = (beforeActiveness * 1.375).roundToInt()
            "Active" -> reqCalorie = (beforeActiveness * 1.55).roundToInt()
            "Very Active" -> reqCalorie = (beforeActiveness * 1.725).roundToInt()
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

    private fun getAge(year: Int, month: Int, day: Int): String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob.set(year, month, day)
        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age.toString()
    }

}