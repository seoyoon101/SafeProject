package com.seoyoon.safeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.seoyoon.safeproject.databinding.ActivityMypageBinding

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        var resultString = ""

        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in

            val userInfo = db.collection("users")
                .document(user.email.toString())
                .get()
                .addOnSuccessListener {document ->
                   binding.emailEditText2.setText (document.get("email").toString())
                   binding.etName2.setText(document.get("name").toString())

                   if(document.get("gender").toString() == "남성"){
                       binding.radioButton2.isChecked = false
                       binding.radioButton.isChecked = true
                   }
                    else{
                       binding.radioButton2.isChecked = true
                       binding.radioButton.isChecked = false
                   }
                   binding.joinBirth2.setText(document.get("born").toString())
                   binding.joinTel2.setText(document.get("emergency1").toString())
                   binding.joinEmergencyTel5.setText(document.get("emergency1").toString())
                   binding.joinEmergencyTel4.setText(document.get("emergency2").toString())
                   binding.joinEmergencyTel.setText (document.get("emergency3").toString())
                }
                .addOnFailureListener {
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                }

            binding.aditAccountButton.setOnClickListener {

                var gender = ""
                binding.rdGroup.setOnCheckedChangeListener { radioGroup, id ->
                    when (id) {
                        binding.radioButton.id -> gender = "여성"
                        binding.radioButton2.id -> gender = "남성"
                    }
                }

                val user = hashMapOf(
                    "email" to binding.emailEditText2.toString(),
                    "name" to binding.etName2.text.toString(),
                    "gender" to gender,
                    "born" to binding.joinBirth2.text.toString(),
                    "phoneNum" to binding.joinTel2.text.toString(),
                    "emergency1" to binding.joinEmergencyTel5.text.toString(),
                    "emergency2" to binding.joinEmergencyTel4.text.toString(),
                    "emergency3" to binding.joinEmergencyTel.text.toString(),
                )

                val washingtonRef = db.collection("users").document(binding.emailEditText2.toString())

                washingtonRef
                    .update(
                        mapOf(
                            "email" to binding.emailEditText2.toString(),
                            "name" to binding.etName2.text.toString(),
                            "gender" to gender,
                            "born" to binding.joinBirth2.text.toString(),
                            "phoneNum" to binding.joinTel2.text.toString(),
                            "emergency1" to binding.joinEmergencyTel5.text.toString(),
                            "emergency2" to binding.joinEmergencyTel4.text.toString(),
                            "emergency3" to binding.joinEmergencyTel.text.toString(),)
                    )

                val intentToMain = Intent(this, MainActivity::class.java)
                startActivity(intentToMain)
                finish()
            }

        } else {
            // No user is signed in

        }


    }
}