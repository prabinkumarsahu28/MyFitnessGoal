package com.eclair.myfitnessgoal.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.eclair.myfitnessgoal.R
import com.eclair.myfitnessgoal.roomdb.FoodApplication
import com.eclair.myfitnessgoal.roomdb.FitnessViewModel
import com.eclair.myfitnessgoal.roomdb.FoodViewModelFactory
import com.eclair.myfitnessgoal.roomdb.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private var profileLocation: Uri? = null
    lateinit var userEntity: UserEntity
    lateinit var fitnessEntity: UserEntity

    private lateinit var viewModel: FitnessViewModel
    private val uid = FirebaseAuth.getInstance().uid
    val password =
        FirebaseDatabase.getInstance().reference.child("Users").child(uid!!).child("password")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)



        Log.d("password", password.toString())
        clickListeners()

        val app = application as FoodApplication
        val repo = app.foodRepo
        val foodViewModelFactory = FoodViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, foodViewModelFactory).get(FitnessViewModel::class.java)


        viewModel.getUserDetails(uid).observe(this, {

            fitnessEntity = it
            etSoubiaEdit.setText(it.userName)
            tvHeightValEdit.setText(it.height)
            tvEmailValEdit.setText(it.emailId)
            tvDobValEdit.setText(it.dob)
            tvSexValEdit.setText(it.sex)
            tvGoalsValEdit.setText(it.goal)
            userData()
        })


    }

    private fun userData() {
//        userEntity = UserEntity(
//            etSoubiaEdit.text.toString(),
//            tvEmailValEdit.text.toString(),
//            uid!!,
//            fitnessEntity.password,
//            tvGoalsValEdit.text.toString(),
//            fitnessEntity.activeness,
//            tvSexValEdit.text.toString(),
//            tvHeightValEdit.text.toString(),
//            fitnessEntity.weight,
//            tvDobValEdit.text.toString(),
//            "2345",
//            location.toString()
//        )
    }

    private fun clickListeners() {
        profile_Edit.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }


        btnSaveEdits.setOnClickListener {

            fitnessEntity.userName = etSoubiaEdit.text.toString()
            fitnessEntity.height = tvHeightValEdit.text.toString()
            fitnessEntity.dob = tvDobValEdit.text.toString()
            fitnessEntity.profilePic = profileLocation.toString()

            viewModel.updateProfile(fitnessEntity)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("editedDetails", 3)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

//        btnSaveEdits.setOnClickListener {
//            viewModel.editProfile(tvHeightValEdit.text.toString(),uid)
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("editedDetails", 3)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            startActivity(intent)
//        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (intent?.data != null) {
            profileLocation = intent.data
            profile_Edit.setImageURI(profileLocation)
        }
    }


}