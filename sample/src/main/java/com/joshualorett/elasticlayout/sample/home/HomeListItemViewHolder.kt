package com.joshualorett.elasticlayout.sample.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshualorett.elasticlayout.sample.R

/**
 * [RecyclerView.ViewHolder] for home list.
 * Created by Joshua on 12/31/2019.
 */
class HomeListItemViewHolder(itemView: View, itemClickListener: ItemClickListener? = null) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.itemText)

    init {
        textView.setOnClickListener { v ->
            itemClickListener?.onItemClick(adapterPosition, textView.text.toString(), v)
        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int, text: String, view: View)
    }
}