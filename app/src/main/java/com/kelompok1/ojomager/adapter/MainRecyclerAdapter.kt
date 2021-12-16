package com.kelompok1.ojomager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.models.AllCategory
import com.kelompok1.ojomager.models.CategoryItem

class MainRecyclerAdapter(private val context: Context, private val allCategoryList: List<AllCategory>) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView
        var itemRecycler:RecyclerView

        init {
            categoryTitle = itemView.findViewById(R.id.cat_title)
            itemRecycler = itemView.findViewById(R.id.item_recycler)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return allCategoryList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {


        holder.categoryTitle.text=allCategoryList[position].categoryTitle
        setCatItemRecycler(holder.itemRecycler,allCategoryList[position].categoryItem)
    }

    private fun setCatItemRecycler(recyclerView: RecyclerView,categoryItem: List<CategoryItem>){
        val itemRecyclerAdapter = CategoryItemAdapter(context, categoryItem)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter =itemRecyclerAdapter
    }
}