package com.about.asetdaerah_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.about.asetdaerah_app.databinding.ActivityLoginBinding
import com.about.asetdaerah_app.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}