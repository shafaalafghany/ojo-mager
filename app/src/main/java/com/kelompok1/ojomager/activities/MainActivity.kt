package com.kelompok1.ojomager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.adapter.MainItemAdapter
import com.kelompok1.ojomager.adapter.SecondItemAdapter
import com.kelompok1.ojomager.adapter.ThirdItemAdapter
import com.kelompok1.ojomager.data.BeginnerListItem
import com.kelompok1.ojomager.data.IntermediateListItem
import com.kelompok1.ojomager.data.ListItem
import com.kelompok1.ojomager.models.Item
import com.kelompok1.ojomager.storage.SharedPrefManager
import com.kelompok1.ojomager.ui.home.SetGoalDialog

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var rvMain : RecyclerView
    private var list : ArrayList<Item> = arrayListOf()
    private var list2 : ArrayList<Item> = arrayListOf()
    private var list3 : ArrayList<Item> = arrayListOf()

    private fun showFirstRecyclerList() {
        rvMain = rv_first_label
        rvMain.layoutManager = LinearLayoutManager(this)
        val listItemAdapter = MainItemAdapter(list)
        rvMain.adapter = listItemAdapter
    }

    private fun showSecondRecyclerList() {
        rvMain = rv_second_label
        rvMain.layoutManager = LinearLayoutManager(this)
        val listItemAdapter = SecondItemAdapter(list2)
        rvMain.adapter = listItemAdapter
    }

    private fun showThirdRecyclerList() {
        rvMain = rv_third_label
        rvMain.layoutManager = LinearLayoutManager(this)
        val listItemAdapter = ThirdItemAdapter(list3)
        rvMain.adapter = listItemAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        rv_first_label.setHasFixedSize(true)
        list.addAll(ListItem.listData)
        list2.addAll(BeginnerListItem.listData)
        list3.addAll(IntermediateListItem.listData)
        showFirstRecyclerList()
        showSecondRecyclerList()
        showThirdRecyclerList()

        btn_set_goal.setOnClickListener {
            val dialog = SetGoalDialog()

            dialog.show(supportFragmentManager, "Set Goal")
        }

        img_btn_quarantine.setOnClickListener {
            Toast.makeText(this, "Quarantine At Home Touched", Toast.LENGTH_SHORT).show()
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        if (SharedPrefManager.getInstance(this)?.isLoggedIn!!) {
            navView.menu.findItem(R.id.report).isVisible = true
            navView.menu.findItem(R.id.reminder).isVisible = true
            navView.menu.findItem(R.id.account).isVisible = true
            navView.menu.findItem(R.id.signout).isVisible = true
            navView.menu.findItem(R.id.signin).isVisible = false
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.account -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.training_plans -> startActivity(Intent(this, MainActivity::class.java))
                R.id.discovery -> {
                    startActivity(Intent(this, DiscoveryActivity::class.java))
                }
                R.id.report -> Toast.makeText(
                    applicationContext,
                    "Going to report screen",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.reminder -> {
                    startActivity(Intent(this, ReminderActivity::class.java))
                }
                R.id.signin -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.signout -> {
                    SharedPrefManager.getInstance(this)?.clear()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            true
        }
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