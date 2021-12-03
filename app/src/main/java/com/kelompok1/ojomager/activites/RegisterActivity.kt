package com.kelompok1.ojomager.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import android.os.Bundle
import android.widget.Toast
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.api.RetrofitClient
import com.kelompok1.ojomager.models.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        btn_create_account.setOnClickListener {
            Toast.makeText(applicationContext, "Create Account Button Touched", Toast.LENGTH_SHORT).show()

            val email = edt_email.text.toString().trim()
            val fullname = edt_fullname.text.toString().trim()
            val password = edt_password.text.toString().trim()

            if (email.isEmpty()) {
                edt_email.error = "Email required"
                edt_email.requestFocus()
                return@setOnClickListener
            }

            if (fullname.isEmpty()) {
                edt_fullname.error = "Fullname required"
                edt_fullname.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                edt_password.error = "Password required"
                edt_password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(
                email, fullname, password
            ).enqueue(object: Callback<DefaultResponse>{
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }

        tv_sign_in.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}