package com.eclair.myfitnessgoal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.adapter.PlanDetailsAdapter
import com.eclair.myfitnessgoal.models.Plans
import kotlinx.android.synthetic.main.activity_plan_details.*

class PlanDetailsActivity : AppCompatActivity() {

    private lateinit var plans: Plans

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_details)

        if (intent != null && intent.extras != null) {
            plans = intent.getSerializableExtra("plan") as Plans
            Glide.with(this).load(plans.img).into(ivPlanImage)
            tvPlanDetailsName.text = plans.name
        }

        vpPlanDetails.adapter = PlanDetailsAdapter(supportFragmentManager, plans)
        tlPlanDetails.setupWithViewPager(vpPlanDetails)

        ivArrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }
}