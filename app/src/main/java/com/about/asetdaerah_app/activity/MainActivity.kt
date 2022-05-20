package com.about.asetdaerah_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.activity.admin.AdminActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ll_data_admin.setOnClickListener {
            startActivity(Intent(this,AdminActivity::class.java))
        }

    }
}