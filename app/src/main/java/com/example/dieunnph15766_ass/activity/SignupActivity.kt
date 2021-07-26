package com.example.dieunnph15766_ass.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(R.layout.activity_signup)

        setSupportActionBar(toolbar_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = ""

        button_signup.setOnClickListener {
            if (edit_text_signup_username.text.toString().isEmpty()) {
                edit_text_signup_username.error = "Bạn chưa nhập tên nguời dùng!"
                return@setOnClickListener
            }

            if (edit_text_signup_password.text.toString().length < 6) {
                edit_text_signup_password.error = "Mật khẩu quá ngắn!"
                return@setOnClickListener
            }

            if (edit_text_signup_password.text.toString() != edit_text_signup_re_password.text.toString()) {
                edit_text_signup_re_password.error = "Mật khẩu không trùng khớp!"
                return@setOnClickListener
            }


        }

        text_view_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}