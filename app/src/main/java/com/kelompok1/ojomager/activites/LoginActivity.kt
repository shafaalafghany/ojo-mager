package com.kelompok1.ojomager.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.os.Bundle
import android.widget.Toast
import com.kelompok1.ojomager.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        btn_signin.setOnClickListener {
            Toast.makeText(applicationContext, "Sign In Button Touched", Toast.LENGTH_SHORT).show()
        }

        tv_create_account.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}