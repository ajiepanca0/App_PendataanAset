package com.about.asetdaerah_app.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.activity.RegisterActivity
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.HasilItemAdmin
import com.about.asetdaerah_app.connection.Respon.ResponseDataAdmin
import kotlinx.android.synthetic.main.activity_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AdminActivity : AppCompatActivity() {

    private var listadmin = ArrayList<dataAdmin>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        btn_add_admin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        getdataAdmin()
    }

    private fun getdataAdmin() {
        val dataAdmin = ApiConfig.getApiService().getDataAdmin()
        dataAdmin.enqueue(object : Callback<ResponseDataAdmin>{
            override fun onResponse(
                call: Call<ResponseDataAdmin>,
                response: Response<ResponseDataAdmin>
            ) {
                if (response.isSuccessful){
                    val responadmin = response.body()
                    if (responadmin !=null){
                            setdataAdmin(responadmin.hasil)
                        Toast.makeText(applicationContext,"sukss", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataAdmin>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })
    }

    private fun setdataAdmin(hasil: List<HasilItemAdmin>) {

        for ( data in hasil) {

            val admin = dataAdmin(data.nip, data.nama, data.password, data.notelp)
            listadmin.add(admin)

        }

        val adapteradmin = ListAdminAdapter(listadmin)
        rvlistadmin.adapter = adapteradmin

            val layoutManager = LinearLayoutManager(this)
            rvlistadmin.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
            rvlistadmin.addItemDecoration(itemDecoration)

            adapteradmin.setOnItemClickCallback(object : ListAdminAdapter.OnItemClickCallback{
                override fun onItemClicked(data: dataAdmin) {

                    Toast.makeText(applicationContext, data.nama, Toast.LENGTH_SHORT).show()
                   // detailMovieRekomend(data)
                }

            })




    }
}