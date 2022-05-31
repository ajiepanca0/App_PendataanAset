package com.about.asetdaerah_app.connection

import com.about.asetdaerah_app.connection.Respon.ResponLogin
import com.about.asetdaerah_app.connection.Respon.ResponseAsetAlat
import com.about.asetdaerah_app.connection.Respon.ResponseDataAdmin
import okhttp3.MultipartBody
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
        @Field("notelp") notelp:String ): Call<ResponLogin>

    @FormUrlEncoded
    @POST("asetbangunan.php")
    fun asetBangunan(
        @Field("idasetbgn") idasetbgn:String,
        @Field("namabgn") namabgn:String,
        @Field("kondisibgn") kondisibgn:String,
        @Field("jenisbgn") jenisbgn:String,
        @Field("luasbgn") luasbgn:String,
        @Field("alamatbgn") alamatbgn:String,
        @Field("biayabgn") biayabgn:String,
        @Field("lainyabgn") lainyabgn:String, ): Call<ResponLogin>

    @GET("data_admin.php")
    fun getDataAdmin(): Call<ResponseDataAdmin>

    @GET("data_aset_alat.php")
    fun getDataAsetAlat(): Call<ResponseAsetAlat>



}