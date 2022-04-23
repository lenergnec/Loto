package com.example.fact_e.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fact_e.Model.DataFac
import com.example.fact_e.R

class AdapterData (private val dataFac: ArrayList<DataFac>): RecyclerView.Adapter<AdapterData.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_card_items,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val views = dataFac[position]

        holder.number.text = views.number.toString()
        holder.amount.text = views.amount.toString()
        holder.sorteo.text = views.sorteo.toString()

    }

    override fun getItemCount(): Int {
        return dataFac.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val number: TextView = itemView.findViewById(R.id.tvNumber)
        val amount: TextView = itemView.findViewById(R.id.tvAmount)
        val sorteo: TextView = itemView.findViewById(R.id.tvSorteo)

    }
}