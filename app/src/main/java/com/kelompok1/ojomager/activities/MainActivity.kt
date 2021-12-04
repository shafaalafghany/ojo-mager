package com.kelompok1.ojomager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.storage.SharedPrefManager

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.hide()

        if (SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            navView.menu.findItem(R.id.report).isVisible = true
            navView.menu.findItem(R.id.reminder).isVisible = true
            navView.menu.findItem(R.id.account).isVisible = true
            navView.menu.findItem(R.id.signout).isVisible = true
            navView.menu.findItem(R.id.signin).isVisible = false
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.account -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.training_plans -> startActivity(Intent(this, MainActivity::class.java))
                R.id.discovery -> Toast.makeText(applicationContext, "Going to discovery screen", Toast.LENGTH_SHORT).show()
                R.id.report -> Toast.makeText(applicationContext, "Going to report screen", Toast.LENGTH_SHORT).show()
                R.id.signin -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.signout -> {
                    SharedPrefManager.getInstance(this)?.clear()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()

        if (!SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}