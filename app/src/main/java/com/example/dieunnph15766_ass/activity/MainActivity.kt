package com.example.dieunnph15766_ass.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Get username from sharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username:String? = sharedPreferences.getString("USERNAME", "")


    }
}