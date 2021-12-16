package com.kelompok1.ojomager.data

import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.models.Item

object IntermediateListItem {
    private val homeItemTitles = arrayOf(
        "ABS INTERMEDIATE",
        "CHEST INTERMEDIATE",
        "ARM INTERMEDIATE",
        "LEG INTERMEDIATE",
        "SHOULDER & BACK INTERMEDIATE"
    )

    private val homeItemImg = intArrayOf(
        R.drawable.item_1,
        R.drawable.item_2,
        R.drawable.item_1,
        R.drawable.item_2,
        R.drawable.item_1,
    )

    val listData: ArrayList<Item>

        get() {
            val list = arrayListOf<Item>()

            for (position in homeItemTitles.indices) {
                val item = Item()
                item.title = homeItemTitles[position]
                item.img = homeItemImg[position]
                list.add(item)
            }

            return list
        }
}