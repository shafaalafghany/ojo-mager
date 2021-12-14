package com.kelompok1.ojomager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.storage.SharedPrefManager

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val data = SharedPrefManager.getInstance(this)?.user

        tv_fullname.text = data?.user_fullname
        tv_email.text = data?.user_email
        tv_phone.text = data?.user_phone
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