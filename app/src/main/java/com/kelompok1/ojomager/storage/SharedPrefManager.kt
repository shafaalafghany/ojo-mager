package com.kelompok1.ojomager.storage

import android.content.Context
import android.content.SharedPreferences

import com.kelompok1.ojomager.models.User


class SharedPrefManager private constructor(private val mCtx: Context) {
    fun saveUser(user: User) {
        val sharedPreferences: SharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("user_id", user.user_id)
        editor.putString("user_email", user.user_email)
        editor.putString("user_fullname", user.user_fullname)
        editor.putString("user_phone", user.user_phone)
        editor.putString("token", user.token)
        editor.apply()
    }

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences: SharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("user_id", -1) != -1
        }
    val user: User
        get() {
            val sharedPreferences: SharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getInt("user_id", -1),
                sharedPreferences.getString("user_email", null)!!,
                sharedPreferences.getString("user_fullname", null)!!,
                sharedPreferences.getString("user_phone", null),
                sharedPreferences.getString("token", null)!!
            )
        }

    val token: String
        get() {
            val sharedPreferences: SharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("token", null)!!
        }

    fun clear() {
        val sharedPreferences: SharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager? {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance
        }
    }
}