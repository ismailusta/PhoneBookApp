package com.example.turkcelllab17

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterUser(var alist : ArrayList<User>, itemClik: (position: Int)->Unit) : RecyclerView.Adapter<RvViewHolder>() {
    var itemClik = itemClik

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val viewHolderForHolder = LayoutInflater.from(parent.context).inflate(R.layout.liste_row, parent, false)
        return RvViewHolder(viewHolderForHolder, itemClik)
    }

    override fun getItemCount(): Int {
        return alist.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val index = alist[position]
        holder.itemView.findViewById<TextView>(R.id.textView).text = index.name.toString()
        holder.itemView.findViewById<TextView>(R.id.textView2).text = index.email.toString()
    }
}