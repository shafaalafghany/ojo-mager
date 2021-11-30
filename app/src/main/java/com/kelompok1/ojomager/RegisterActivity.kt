package com.kelompok1.ojomager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        val btnSignin = findViewById<TextView>(R.id.tv_sign_in)

        btnSignin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}