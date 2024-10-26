package com.example.turkcelllab17

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvViewHolder(itemView: View, itemClik: (position: Int)->Unit) : RecyclerView.ViewHolder(itemView) {
    var Ad : TextView
    var Eposta : TextView
    init {
        Ad = itemView.findViewById<TextView>(R.id.textView)
        Eposta = itemView.findViewById<TextView>(R.id.textView2)
        itemView.setOnClickListener {
            itemClik(adapterPosition)
        }
    }
}