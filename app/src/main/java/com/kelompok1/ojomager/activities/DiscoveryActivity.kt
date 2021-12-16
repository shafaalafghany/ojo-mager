package com.kelompok1.ojomager.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.adapter.MainRecyclerAdapter
import com.kelompok1.ojomager.models.AllCategory
import com.kelompok1.ojomager.models.CategoryItem
import com.kelompok1.ojomager.storage.SharedPrefManager
import java.util.ArrayList

class DiscoveryActivity : AppCompatActivity() {
    private var mainCategoryRecycler: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discovery)

        supportActionBar?.hide()

        val categoryItemList1: MutableList<CategoryItem> = ArrayList()
        categoryItemList1.add(CategoryItem(1, R.drawable.begin1))
        categoryItemList1.add(CategoryItem(1, R.drawable.begin2))
        categoryItemList1.add(CategoryItem(1, R.drawable.begin3))
        categoryItemList1.add(CategoryItem(1, R.drawable.begin4))
        categoryItemList1.add(CategoryItem(1, R.drawable.begin5))


        val categoryItemList2: MutableList<CategoryItem> = ArrayList()
        categoryItemList2.add(CategoryItem(1, R.drawable.chal1))
        categoryItemList2.add(CategoryItem(1, R.drawable.chal2))
        categoryItemList2.add(CategoryItem(1, R.drawable.chal3))
        categoryItemList2.add(CategoryItem(1, R.drawable.chal4))


        val categoryItemList3: MutableList<CategoryItem> = ArrayList()
        categoryItemList3.add(CategoryItem(1, R.drawable.stretch1))
        categoryItemList3.add(CategoryItem(1, R.drawable.stretch2))
        categoryItemList3.add(CategoryItem(1, R.drawable.stretch3))
        categoryItemList3.add(CategoryItem(1, R.drawable.stretch4))


        val categoryItemList4: MutableList<CategoryItem> = ArrayList()
        categoryItemList4.add(CategoryItem(1, R.drawable.focus1))
        categoryItemList4.add(CategoryItem(1, R.drawable.focus2))
        categoryItemList4.add(CategoryItem(1, R.drawable.focus3))
        categoryItemList4.add(CategoryItem(1, R.drawable.focus4))


        val allCategory: MutableList<AllCategory> = ArrayList()
        allCategory.add(AllCategory("For Beginners", categoryItemList1))
        allCategory.add(AllCategory("Challenge",categoryItemList2))
        allCategory.add(AllCategory("Stretch",categoryItemList3))
        allCategory.add(AllCategory("Body Focus",categoryItemList4))

        setMainCategoryRecycler(allCategory)
    }

    private fun setMainCategoryRecycler(allCategoryList: List<AllCategory>) {

        mainCategoryRecycler = findViewById(R.id.main_recycler)
        mainCategoryRecycler?.layoutManager = LinearLayoutManager(this)
        mainCategoryRecycler?.adapter = MainRecyclerAdapter(this, allCategoryList)
    }

    override fun onStart() {
        super.onStart()

        if (!SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}