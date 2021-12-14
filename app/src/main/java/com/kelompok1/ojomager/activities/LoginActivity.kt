package com.kelompok1.ojomager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.api.RetrofitClient
import com.kelompok1.ojomager.models.LoginResponse
import com.kelompok1.ojomager.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.edt_email
import kotlinx.android.synthetic.main.activity_login.edt_password
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        btn_signin.setOnClickListener {
            val email = edt_email.text.toString().trim()
            val password = edt_password.text.toString().trim()

            if (email.isEmpty()) {
                edt_email.error = "Email required"
                edt_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                edt_password.error = "Password required"
                edt_password.requestFocus()
                return@setOnClickListener
            }

            signIn(email, password)
        }

        tv_create_account.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val observablePass = observePass()

        observablePass.distinctUntilChanged()
            .subscribe { query ->
                signIn(edt_email.text.toString(), query)
            }
    }

    private fun observePass(): Observable<String> {
        return PublishSubject.create{ emitter ->
            edt_password.addTextChangedListener {
                emitter.onNext(edt_password.text.toString())
            }
        }
    }

    private fun signIn(email: String, password: String) {
        RetrofitClient.instance.signIn(
            email, password
        ).enqueue(object: Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.code() == 200){
                    SharedPrefManager.getInstance(applicationContext)?.saveUser(response.body()?.data!!)
                    Toast.makeText(applicationContext, "Sign in Success!", Toast.LENGTH_LONG).show()

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}