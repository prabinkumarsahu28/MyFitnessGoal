package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclair.myfitnessgoal.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnSignupStart.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        btnLoginStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}