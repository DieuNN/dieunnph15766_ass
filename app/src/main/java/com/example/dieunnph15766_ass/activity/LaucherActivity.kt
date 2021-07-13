package com.example.dieunnph15766_ass.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.UserDB
import com.example.dieunnph15766_ass.model.User

class LaucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //using Database Inspector to check whether I created database
        val database:Database = Database(this)
        val userDB:UserDB = UserDB(database)
        Log.e("e", userDB.newUser(User(123, "Dieu", "03e234")).toString())



    }
}