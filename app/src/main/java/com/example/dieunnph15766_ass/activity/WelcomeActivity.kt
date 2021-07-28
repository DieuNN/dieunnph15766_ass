package com.example.dieunnph15766_ass.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Transparent navigation and status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_welcome)

        // 2 buttons open 2 activities
        button_new_user.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        button_already_user.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}