package com.about.asetdaerah_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.about.asetdaerah_app.Helper.Constant
import com.about.asetdaerah_app.Helper.PreferencesHelper
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.ResponLogin
import com.about.asetdaerah_app.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedpref : PreferencesHelper
    private val api by lazy { ApiConfig().endPoint }

    var sukses : String = "sukses"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = PreferencesHelper(this )


        binding.tvMovetoReg.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            checkFormlogin()
        }
    }

    private fun checkFormlogin() {
        when{
            binding.etLoginNip.text.toString().length<16->{
                binding.etLoginNip.error = "nip less than 16"
            }
            binding.etLoginPass.text.toString().length<8 ->{
                binding.etLoginPass.error = "password less than 8"
            }
            else ->{
                loginProcess()
            }
        }
    }

    private fun loginProcess() {
            showloading(true)
        api.loginPegawai(
            binding.etLoginNip.text.toString(),
            binding.etLoginPass.text.toString()
        ).enqueue(object : Callback<ResponLogin>{
            override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                val data =response.body()
                if (response.isSuccessful){
                    if (data !=null){
                        if (data.status == sukses){
                            showloading(false)
                            Toast.makeText(applicationContext, data.status, Toast.LENGTH_SHORT).show()
                            saveSession(binding.etLoginNip.text.toString())
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
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun saveSession(nip: String) {

        sharedpref.put(Constant.PREF_NIP,nip )
        sharedpref.put(Constant.PREF_IS_LOGIN, true)

    }

    private fun showloading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

}