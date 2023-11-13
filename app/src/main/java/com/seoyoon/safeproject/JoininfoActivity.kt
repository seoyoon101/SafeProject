package com.seoyoon.safeproject

import android.content.Intent
import android.graphics.Paint.Join
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoyoon.safeproject.databinding.ActivityJoininfoBinding
import com.seoyoon.safeproject.databinding.ActivityLoginBinding

class JoininfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoininfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoininfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        val email = intent.getStringExtra("email")
        var gender = "여성"
        binding.radioFemale.isChecked = true

        binding.radioGender.setOnCheckedChangeListener { radioGroup, id ->
            when (id) {
                binding.radioFemale.id -> gender = "여성"
                binding.radioMale.id -> gender = "남성"
            }
        }
        }

            val user = hashMapOf(
                "email" to email,
                "name" to binding.etName.text.toString(),
                "gender" to gender,
                "born" to binding.joinBirth.text.toString(),
                "phoneNum" to binding.joinTel.text.toString(),
                "emergency1" to binding.joinEmergencyTel1.text.toString(),
                "emergency2" to binding.joinEmergencyTel2.text.toString(),
                "emergency3" to binding.joinEmergencyTel3.text.toString(),
            )

            db.collection("users")
                .document(email.toString())
                .set(user)
            val intentToMain = Intent(this, LoginActivity::class.java)
            startActivity(intentToMain)
            finish()
        }

    }
}