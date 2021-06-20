package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.fragments.MeFragment
import kotlinx.android.synthetic.main.activity_add_weight.*

class AddWeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weight)

        btnSaveWeight.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("AddFood", 4)
            intent.putExtra("weight", etAddWeight.text.toString().toInt())
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}