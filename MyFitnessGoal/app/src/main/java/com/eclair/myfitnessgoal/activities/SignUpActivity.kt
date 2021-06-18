package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.fragments.DetailsFragment
import com.eclair.myfitnessgoal.fragments.GoalFragment
import com.eclair.myfitnessgoal.models.Users

class SignUpActivity : AppCompatActivity() {

    lateinit var user: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var type: String? = null
        if (intent != null && intent.extras != null) {
            type = intent.getStringExtra("type")
            user = intent.getSerializableExtra("user") as Users
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (type == "google") {
            val goalFragment = GoalFragment()
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            goalFragment.arguments = bundle
            fragmentTransaction.replace(R.id.flSignUp, goalFragment).commit()
        } else {
            fragmentTransaction.replace(R.id.flSignUp, DetailsFragment()).commit()
        }
    }
}