package com.kelompok1.ojomager.api

import com.kelompok1.ojomager.models.DefaultResponse
import com.kelompok1.ojomager.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("users/")
    fun createUser(
        @Field("email") email: String,
        @Field("fullname") fullname: String,
        @Field("password") password: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("users/signin/")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}