package com.example.dieunnph15766_ass.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.UserDB
import com.example.dieunnph15766_ass.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {
    private val sqliteDatabase = Database(this)
    val userDB = UserDB(sqliteDatabase)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setSupportActionBar(toolbar_login)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = ""

        edit_text_login_username.setText(intent.getStringExtra("username"))
        edit_text_login_password.setText(intent.getStringExtra("password"))

        button_login.setOnClickListener {
            if (edit_text_login_username.text.toString().isEmpty()) {
                edit_text_login_username.error = "Bạn chưa nhập tên nguời dùng!"
                return@setOnClickListener
            }

            if (edit_text_login_password.text.toString().isEmpty()) {
                edit_text_login_password.error = "Mật khẩu quá ngắn!"
                return@setOnClickListener
            }

            if (userDB.isUserExist(
                    User(
                        null,
                        edit_text_login_username.text.toString(),
                        edit_text_login_password.text.toString()
                    )
                )
            ) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
            }
        }

        text_view_signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

    }
}