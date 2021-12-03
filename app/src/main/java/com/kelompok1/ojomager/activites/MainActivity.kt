package com.kelompok1.ojomager.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.kelompok1.ojomager.R

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.hide()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.training_plans -> startActivity(Intent(this, MainActivity::class.java))
                R.id.discovery -> Toast.makeText(applicationContext, "Going to discovery", Toast.LENGTH_SHORT).show()
                R.id.signin -> startActivity(Intent(this, LoginActivity::class.java))
            }
            true
        }
    }
}