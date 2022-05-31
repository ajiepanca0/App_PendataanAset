package com.about.asetdaerah_app.activity.alat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.asetdaerah_app.R
import com.about.asetdaerah_app.activity.RegisterActivity
import com.about.asetdaerah_app.activity.admin.ListAdminAdapter
import com.about.asetdaerah_app.activity.admin.dataAdmin
import com.about.asetdaerah_app.connection.ApiConfig
import com.about.asetdaerah_app.connection.Respon.HasilItemAlat
import com.about.asetdaerah_app.connection.Respon.ResponseAsetAlat
import com.about.asetdaerah_app.connection.Respon.ResponseDataAdmin
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_alat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AlatActivity : AppCompatActivity() {

    private var listasetalat = ArrayList<dataAlat>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alat)

        btn_add_alat.setOnClickListener {
            startActivity(Intent(this, AddAsetAlatActivity::class.java))
        }

        getdataAlat()
    }

    private fun getdataAlat() {
        val dataAdmin = ApiConfig.getApiService().getDataAsetAlat()
        dataAdmin.enqueue(object : Callback<ResponseAsetAlat> {
            override fun onResponse(
                call: Call<ResponseAsetAlat>,
                response: Response<ResponseAsetAlat>
            ) {
                if (response.isSuccessful){
                    val respondatalat = response.body()
                    if (respondatalat !=null){
                        setdataAsetalat(respondatalat.hasil)
                        Toast.makeText(applicationContext,"sukses", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseAsetAlat>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })
    }

    private fun setdataAsetalat(hasil: List<HasilItemAlat>) {

        for ( data in hasil) {

            val alat = dataAlat(data.idasetalat,data.namaalat, data.kondisialat, data.jumlahalat, data.biayaalat, data.lainnyalat)
            listasetalat.add(alat)

        }

        val adapteradmin = ListAlatAdapter(listasetalat)
        rvlistadmin.adapter = adapteradmin

        val layoutManager = LinearLayoutManager(this)
        rvlistadmin.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        rvlistadmin.addItemDecoration(itemDecoration)

        adapteradmin.setOnItemClickCallback(object : ListAlatAdapter.OnItemClickCallback{
            override fun onItemClicked(data: dataAlat) {

                Toast.makeText(applicationContext, data.namaalat, Toast.LENGTH_SHORT).show()
                // detailMovieRekomend(data)
            }

        })




    }


}