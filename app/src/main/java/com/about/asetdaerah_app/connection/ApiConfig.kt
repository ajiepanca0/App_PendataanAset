package com.about.asetdaerah_app.connection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    val endPoint: ApiInterfaces

        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor).build()


            val retrofit = Retrofit.Builder()
                .baseUrl("http://IP-Belum/folder/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create  (ApiInterfaces::class.java)
        }
}

