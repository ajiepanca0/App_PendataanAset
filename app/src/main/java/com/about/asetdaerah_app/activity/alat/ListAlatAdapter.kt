package com.about.asetdaerah_app.activity.alat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.about.asetdaerah_app.R
import java.util.ArrayList

class ListAlatAdapter(private val listAlat : ArrayList<dataAlat>): RecyclerView.Adapter<ListAlatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_admin, viewGroup, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val(idalat,namaalat) = listAlat[position]

        viewHolder.itemidalat.text = idalat
        viewHolder.itemnamalat.text = namaalat


        viewHolder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAlat[viewHolder.adapterPosition]) }



    }

    override fun getItemCount(): Int {
        return listAlat.size
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val itemidalat : TextView = view.findViewById(R.id.tv_row_idalat)
        val itemnamalat : TextView = view.findViewById(R.id.tv_row_namaalat)


    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: dataAlat)
    }
}