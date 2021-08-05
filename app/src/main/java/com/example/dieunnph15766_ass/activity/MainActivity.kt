package com.example.dieunnph15766_ass.activity

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.IncomeTypeDB
import com.example.dieunnph15766_ass.fragment.*
import com.example.dieunnph15766_ass.model.IncomeType
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_drawer_header.view.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set status bar color
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = resources.getColor(R.color.dark_green)
        }
        setContentView(R.layout.activity_main)

        // Get username from sharedPreferences and set text for header navigation view
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username: String? = sharedPreferences.getString("USERNAME", "")
        navigation_view.getHeaderView(0).textview_username_nav_header.text = username

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ChartFragment())
                .commit()
            navigation_view.setCheckedItem(R.id.menu_chart)
        }
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        }
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_income -> supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, IncomeFragmentContainer()).commit()
            R.id.menu_outcome -> supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ExpenseFragmentContainer()).commit()
            R.id.menu_chart -> supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ChartFragment()).commit()
            R.id.menu_information -> supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, InformationFragment()).commit()
            else -> AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Bạn chắc chắn muốn thoát?")
                .setNegativeButton(
                    "Không",
                    DialogInterface.OnClickListener { dialogInterface, _ -> let { dialogInterface.dismiss() } })
                .setPositiveButton(
                    "Thoát",
                    DialogInterface.OnClickListener { _, _ -> exitProcess(0) })
                .create()
                .show()
        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }


}