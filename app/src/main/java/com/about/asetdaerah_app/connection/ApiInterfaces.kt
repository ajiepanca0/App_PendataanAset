package com.about.asetdaerah_app.connection

import com.about.asetdaerah_app.connection.Respon.ResponLogin
import retrofit2.Call
import retrofit2.http.*


interface ApiInterfaces {

    @FormUrlEncoded
    @POST("login.php")
    fun loginPegawai(
        @Field("nip") nip:String,
        @Field("password") password:String): Call<ResponLogin>


    @FormUrlEncoded
    @POST("register.php")
    fun registerPegawai(
        @Field("nip") nip:String,
        @Field("nama") nama:String,
        @Field("password") password:String,
        @Field("notelp") notelp:String,): Call<ResponLogin>
}