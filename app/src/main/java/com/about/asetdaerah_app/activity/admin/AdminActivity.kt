package com.about.asetdaerah_app.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.activity.RegisterActivity
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        btn_add_admin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}