package com.eclair.myfitnessgoal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.activities.MainActivity
import com.eclair.myfitnessgoal.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btnSignUpDetails.setOnClickListener {
            if (checkValidation()) {

                auth.createUserWithEmailAndPassword(
                    etEmailDetails.text.toString(),
                    etPasswordDetails.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {

                        auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = Users(
                                        etUsernameDetails.text.toString(),
                                        etEmailDetails.text.toString(),
                                        etPasswordDetails.text.toString()
                                    )

                                    val id = it.result?.user?.uid

                                    database.reference.child("Users").child(id!!).setValue(user)

                                    val goalFragment = GoalFragment()
                                    val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                                    fragmentTransaction.add(R.id.flSignUp,goalFragment).addToBackStack("goalFragment").commit()

                                    Toast.makeText(context,
                                        "Successfully SignedUp, Kindly verify your email",
                                        Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context,
                                        it.exception?.message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun checkValidation(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        if (etEmailDetails.text!!.isEmpty() ||
            !etEmailDetails.text.toString().trim().matches(emailPattern)
        ) {

            etEmailDetails.error = "invalid email"
            return false
        }
        if (etPasswordDetails.text.toString().length < 7) {

            etPasswordDetails.error = "minimum 6 characters"
            return false
        }
        if (etUsernameDetails.text!!.isEmpty()) {

            etUsernameDetails.error = "must fill"
            return false
        }
        return true
    }

}
