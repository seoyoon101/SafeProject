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

        binding.signupButton2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}