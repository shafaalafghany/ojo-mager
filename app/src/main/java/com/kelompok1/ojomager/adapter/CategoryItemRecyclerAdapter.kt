package com.kelompok1.ojomager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.models.CategoryItem

class CategoryItemAdapter(private val context: Context,private val CategoryItem: List<CategoryItem>): RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    class CategoryItemViewHolder (itemView:View): RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView = itemView.findViewById(R.id.item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.itemImage.setImageResource(CategoryItem[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return CategoryItem.size
    }
}