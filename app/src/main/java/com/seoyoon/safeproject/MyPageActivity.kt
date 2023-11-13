package com.seoyoon.safeproject

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
                   binding.emailEditText2.hint = document.get("email").toString()
                   binding.etName2.hint = document.get("name").toString()
                   binding.emailEditText2.hint = document.get("gender").toString()
                   binding.joinBirth2.hint = document.get("born").toString()
                   binding.emailEditText2.hint = document.get("phoneNum").toString()
                   binding.joinEmergencyTel5.hint = document.get("emergency1").toString()
                   binding.emailEditText2.hint = document.get("emergency2").toString()
                   binding.emailEditText2.hint = document.get("emergency3").toString()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                }

        } else {
            // No user is signed in

        }


    }
}