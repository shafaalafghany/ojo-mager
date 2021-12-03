package com.kelompok1.ojomager.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    private const val BASE_URl = "https://ojo-mager-backend.herokuapp.com/"
    private const val BASE_URl = "http://192.168.56.1:3000/api/"
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfaWQiOjIsInVzZXJfZnVsbG5hbWUiOiJTaGFmYSBBbGFmZ2hhbnkiLCJ1c2VyX2VtYWlsIjoic2hhZmFAZG9tYWluLmNvbSIsInVzZXJfcGhvbmUiOm51bGx9LCJpYXQiOjE2Mzg1MzE0ODAsImV4cCI6MTYzODU3NDY4MH0.n1TEyoOPa9D0kewrcjh4j0NNm6ZkCxd-konRKATBn_Y"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "")
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}