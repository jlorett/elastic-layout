package com.joshualorett.elasticlayout.sample.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshualorett.elasticlayout.sample.R

/**
 * [RecyclerView.Adapter] for home List.
 * Created by Joshua on 12/31/2019.
 */
class HomeListAdapter(private val data: List<String>,
                      private val clickListener: HomeListItemViewHolder.ItemClickListener?= null) : RecyclerView.Adapter<HomeListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return HomeListItemViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeListItemViewHolder, position: Int) {
        val text = data[position]
        holder.textView.text = text
    }
}