package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.MainActivity
import com.eclair.myfitnessgoal.models.Users
import kotlinx.android.synthetic.main.fragment_height_weight.*
import java.util.*
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
class HeightWeightFragment : Fragment() {

    private lateinit var user: Users
    private var age: String? = null
    private var dob: String? = null
    private var reqCalorie: Int = 0
    private var height: Int = 0
    private var weight: Int = 0

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
        Toast.makeText(context,
            "${user.userName}, ${user.goalType}, ${user.activeness}, ${user.sex}",
            Toast.LENGTH_LONG).show()

        clickListener()

        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { _, year, month, day ->
            age = getAge(year, month, day)
            dob = "$day/${month + 1}/$year"
        }

        val users = Users(
            user.userName,
            user.email,
            user.password,
            user.goalType,
            user.activeness,
            user.sex,
            height.toString(),
            weight.toString(),
            dob,
            reqCalorie.toString()
        )
    }

    private fun clickListener() {

        btnNextHeightWt.setOnClickListener {
            if (checkValidation()) {

                getCalorie()
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                Toast.makeText(context,
                    "age: $age, reqCalorie: $reqCalorie",
                    Toast.LENGTH_LONG).show()
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