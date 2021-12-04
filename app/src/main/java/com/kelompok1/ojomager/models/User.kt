package com.kelompok1.ojomager.models

data class User(
    val user_id: Int,
    val user_fullname: String,
    val user_email: String,
    val user_phone: String?,
    val token: String,
)
