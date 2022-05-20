package com.about.asetdaerah_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.about.asetdaerah_app.Helper.Constant
import com.about.asetdaerah_app.Helper.PreferencesHelper
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.ResponLogin
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    lateinit var sharedpref : PreferencesHelper
    private val api by lazy { ApiConfig().endPoint }

    var sukses : String = "sukses"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedpref = PreferencesHelper(this )


       tv_movetoReg.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        btn_login.setOnClickListener {
            checkFormlogin()
        }
    }

    private fun checkFormlogin() {
        when{
          et_loginNip.text.toString().length<16->{
                et_loginNip.error = "nip less than 16"
            }
         et_loginPass.text.toString().length<8 ->{
              et_loginPass.error = "password less than 8"
            }
            else ->{
                loginProcess()
            }
        }
    }

    private fun loginProcess() {
            showloading(true)
        api.loginPegawai(
           et_loginNip.text.toString(),
            et_loginPass.text.toString()
        ).enqueue(object : Callback<ResponLogin>{
            override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                val data =response.body()
                if (response.isSuccessful){
                    if (data !=null){
                        if (data.status == sukses){
                            showloading(false)
                            Toast.makeText(applicationContext, data.status, Toast.LENGTH_SHORT).show()
                            saveSession(et_loginNip.text.toString())
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }else{
                            showloading(false)
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        showloading(false)
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    showloading(false)
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
                showloading(false)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onStart() {
        super.onStart()
        if(sharedpref.getBoolean(Constant.PREF_IS_LOGIN)){
            moveToHome()
        }
    }

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveSession(nip: String) {

        sharedpref.put(Constant.PREF_NIP,nip )
        sharedpref.put(Constant.PREF_IS_LOGIN, true)

    }

    private fun showloading(isLoading: Boolean) {
        if (isLoading) {
          loading.visibility = View.VISIBLE
        } else {
          loading.visibility = View.GONE
        }
    }

}