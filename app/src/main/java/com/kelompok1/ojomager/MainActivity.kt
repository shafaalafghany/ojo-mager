package com.kelompok1.ojomager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSwitch = findViewById<Button>(R.id.btn_switch)

        btnSwitch.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}