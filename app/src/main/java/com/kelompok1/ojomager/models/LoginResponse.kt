package com.kelompok1.ojomager.models

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val data: User
)
