package com.kelompok1.ojomager.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.api.RetrofitClient
import com.kelompok1.ojomager.models.DefaultResponse
import com.kelompok1.ojomager.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.edt_email
import kotlinx.android.synthetic.main.activity_register.edt_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val emailLiveData = MutableLiveData<String>()
    private val fullnameLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()

    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false
        addSource(emailLiveData) { email ->
            val fullname = fullnameLiveData.value
            val password = passwordLiveData.value
            this.value = validateForm(email, fullname, password)
        }

        addSource(fullnameLiveData) { fullname ->
            val email = emailLiveData.value
            val password = passwordLiveData.value
            this.value = validateForm(email, fullname, password)
        }

        addSource(passwordLiveData) { password ->
            val email = emailLiveData.value
            val fullname = fullnameLiveData.value
            this.value = validateForm(email, fullname, password)
        }
    }

    private fun validateForm(email: String?, fullname: String?, password: String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidName = fullname != null
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidEmail && isValidName && isValidPassword
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        edt_email?.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }

        edt_fullname?.doOnTextChanged { text, _, _, _ ->
            fullnameLiveData.value = text?.toString()
        }

        edt_password?.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }

        isValidLiveData.observe(this) { isValid ->
            btn_create_account.isEnabled = isValid
            if (!isValid) {
                btn_create_account.setBackgroundColor(Color.parseColor("#D0D0D0"))
            } else {
                btn_create_account.setBackgroundColor(Color.parseColor("#900C3E"))
            }
        }

        btn_create_account.setOnClickListener {

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
                    Toast.makeText(applicationContext, "Now you can login here", Toast.LENGTH_LONG).show()
                    if (response.body()?.status == true) {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }
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

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}