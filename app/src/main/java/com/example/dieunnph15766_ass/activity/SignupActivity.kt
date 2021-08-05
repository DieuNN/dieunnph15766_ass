package com.example.dieunnph15766_ass.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.user.UserDB
import com.example.dieunnph15766_ass.model.user.User
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Transparent navigation bar and status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(R.layout.activity_signup)

        // Set support action bar, set title
        setSupportActionBar(toolbar_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = ""

        // Database
        val database = Database(this)
        val userDB = UserDB(database)

        // Validating
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

            // Try to add new user, USERNAME column is unique, so if there is a username existed, notify that username was taken.
            if(userDB.newUser(User(null, edit_text_signup_username.text.toString(), edit_text_signup_password.text.toString()))) {
                Toast.makeText(this, "Đăng ký thành công! Mời bạn đăng nhập để sử dụng ứng dụng!", Toast.LENGTH_SHORT).show()

                // New intent to storage username and password if user want to login immediately, and open LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("username", edit_text_signup_username.text.toString())
                intent.putExtra("password", edit_text_signup_password.text.toString())
                startActivity(intent)
            }
            // Username has taken
            else {
                Toast.makeText(this, "Tên ngưòi dùng đã được đặt truớc! Xin hãy chọn tên nguời dùng khác!", Toast.LENGTH_SHORT).show()
            }
        }

        // Login? Click here
        text_view_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}