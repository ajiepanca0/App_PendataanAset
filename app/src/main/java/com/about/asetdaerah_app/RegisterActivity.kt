package com.about.asetdaerah_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.ResponLogin
import com.about.asetdaerah_app.databinding.ActivityLoginBinding
import com.about.asetdaerah_app.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val api by lazy { ApiConfig().endPoint }
    var sukses : String = "sukses"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            checkFormlogin()
        }


    }

    private fun checkFormlogin() {
        when{
            binding.etRegnip.text.toString().isEmpty()->{
                binding.etRegnip.error = "NIP less than 16"
            }
            binding.etRegNamalengkap.text.toString().length<16 ->{
                binding.etRegNamalengkap.error = "full name still not filled"
            }
            binding.etRegPass.text.toString().length<8->{
                binding.etRegPass.error = "password less than 8"
            }
            binding.etRegNotelp.text.toString().length<10->{
                binding.etRegNotelp.error = "phone number less than 10"
            }
            else ->{
               registerProcess()
            }
        }
    }

    private fun registerProcess() {




        showloading(true)
        api.registerPegawai(
            binding.etRegnip.text.toString()   ,
                    binding.etRegNamalengkap.text.toString(),
            binding.etRegPass.text.toString(),
            binding.etRegNotelp.text.toString()
        ).enqueue(object : Callback<ResponLogin>{
            override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                val data =response.body()
                if (response.isSuccessful){
                    if (data !=null){
                        if (data.status.equals("sukses")){
                            showloading(false)
                            Toast.makeText(applicationContext, data.status, Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            showloading(false)
                            Toast.makeText(applicationContext, data.status, Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        showloading(false)
                        Toast.makeText(applicationContext, "Error2", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    showloading(false)
                    Toast.makeText(applicationContext, "Error3", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })
    }

    private fun showloading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }
}