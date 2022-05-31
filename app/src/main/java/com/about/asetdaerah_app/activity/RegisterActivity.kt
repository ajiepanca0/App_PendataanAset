package com.about.asetdaerah_app.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.about.asetdaerah_app.Helper.reduceFileImage
import com.about.asetdaerah_app.Helper.uriToFile
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.ResponLogin
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.custom_toast.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegisterActivity : AppCompatActivity() {


    var sukses : String = "sukses"
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btn_register.setOnClickListener {
        checkFormlogin()
        }

        tv_masuk.setOnClickListener { startGallery() }

    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this)

            getFile = myFile

        img_reg.setImageURI(selectedImg)
        }
    }

    private fun checkFormlogin() {
        when{
            et_regnip.text.toString().length<16->{
                et_regnip.error = "NIP less than 16"
            }
            et_regNamalengkap.text.toString().isEmpty() ->{
                et_regNamalengkap.error = "full name still not filled"
            }
            et_regPass.text.toString().length<8->{
                et_regPass.error = "password less than 8"
            }
            et_regNotelp.text.toString().length<10->{
                et_regNotelp.error = "phone number less than 10"
            }
            else ->{
               registerProcess()
            }
        }
    }

    private fun registerProcess() {

        val file = reduceFileImage(getFile as File)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "foto",
            file.name,
            requestImageFile
        )

        showloading(true)
        ApiConfig.getApiService().registerPegawai(
            et_regnip.text.toString(),
            et_regNamalengkap.text.toString(),
            et_regPass.text.toString(),
            et_regNotelp.text.toString()
        ).enqueue(object : Callback<ResponLogin>{
            override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                val data =response.body()
                if (response.isSuccessful){
                    if (data !=null){
                        if (data.status.equals("sukses")){
                            showloading(false)
                            val time = 1000
                            val sukses =layoutInflater.inflate(R.layout.custom_toast,ll_wrapper)

                            val handler = Handler(Looper.getMainLooper())
                            handler.postDelayed({
                                Toast(applicationContext).apply {
                                    duration = Toast.LENGTH_LONG
                                    setGravity(Gravity.CENTER,0,0)
                                    view = sukses
                                }.show()
                                finish()
                            }, time.toLong())
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
           loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }
}