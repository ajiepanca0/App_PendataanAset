package com.about.asetdaerah_app.activity.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.about.asetdaerah_app.R
import java.util.ArrayList

class ListAdminAdapter(private val listadmin : ArrayList<dataAdmin>): RecyclerView.Adapter<ListAdminAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_admin, viewGroup, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val(nip,nama) = listadmin[position]

        viewHolder.itemnip.text = nip
        viewHolder.itemnama.text = nama



        viewHolder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listadmin[viewHolder.adapterPosition]) }



    }

    override fun getItemCount(): Int {
        return listadmin.size
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val itemnip : TextView = view.findViewById(R.id.tv_row_admin_nip)
        val itemnama : TextView = view.findViewById(R.id.tv_row_admin)


    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: dataAdmin)
    }
}