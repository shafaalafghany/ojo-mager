package com.kelompok1.ojomager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.models.Item

class ThirdItemAdapter(private val listItem: ArrayList<Item>) : RecyclerView.Adapter<ThirdItemAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_vertical_second, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listItem[position]

        Glide.with(holder.itemView.context)
            .load(item.img)
            .into(holder.imgBtn)

        holder.imgBtn.setOnClickListener {
            Toast.makeText(holder.itemView.context, item.title, Toast.LENGTH_SHORT).show()
        }

        holder.tvTitle.text = item.title
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tv_title_item)
        val imgBtn : ImageButton = itemView.findViewById(R.id.img_btn_item)
    }
}