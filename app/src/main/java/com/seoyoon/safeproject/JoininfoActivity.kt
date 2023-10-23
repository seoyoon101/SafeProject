package com.seoyoon.safeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.seoyoon.safeproject.databinding.ActivityJoininfoBinding

class JoininfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoininfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoininfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        val intent = Intent(this, LoginActivity::class.java)
        val email = intent.getStringExtra("email")
        var gender = "여성"
        binding.radioFemale.isChecked = true

        binding.radioGender.setOnCheckedChangeListener { radioGroup, id ->
            when (id) {
                binding.radioFemale.id -> gender = "여성"
                binding.radioMale.id -> gender = "남성"
            }
        }

        binding.signupButton2.setOnClickListener {

            val user = hashMapOf(
                "name" to binding.etName.text.toString(),
                "gender" to gender,
                "born" to binding.joinBirth.text.toString(),
                "phoneNum" to binding.joinTel.text.toString(),
                "emergency1" to binding.joinEmergencyTel1.text.toString(),
                "emergency2" to binding.joinEmergencyTel2.text.toString(),
                "emergency3" to binding.joinEmergencyTel3.text.toString(),
            )

            db.collection(email.toString())
                .add(user)

            startActivity(intent)
            finish() }
    }
}