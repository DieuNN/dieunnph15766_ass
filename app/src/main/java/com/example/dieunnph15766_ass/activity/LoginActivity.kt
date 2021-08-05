package com.example.dieunnph15766_ass.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.user.UserDB
import com.example.dieunnph15766_ass.model.user.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {

    // Define database
    private val sqliteDatabase = Database(this)
    val userDB = UserDB(sqliteDatabase)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Use sharedPreferences to storage information
        val globalSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val localSharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        if (localSharedPreferences.getBoolean("REMEMBER", false)) {
            checkbox_login_remember_info.isChecked = true
            edit_text_login_username.setText(
                localSharedPreferences.getString(
                    "USERNAME",
                    ""
                )
            )
            edit_text_login_password.setText(
                localSharedPreferences.getString(
                    "PASSWORD",
                    ""
                )
            )
        }


        // Transparent navigation bar and status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        // Set support action bar, set navigation button behavior, set title
        setSupportActionBar(toolbar_login)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = ""

        // Get username and password from SignupActivity if new user has been created
        if (intent.getStringExtra("username") != null) {
            edit_text_login_username.setText(intent.getStringExtra("username"))
            edit_text_login_password.setText(intent.getStringExtra("password"))
        }

        //Validating username and password
        button_login.setOnClickListener {
            if (edit_text_login_username.text.toString().isEmpty()) {
                edit_text_login_username.error = "Bạn chưa nhập tên nguời dùng!"
                return@setOnClickListener
            }

            if (edit_text_login_password.text.toString().isEmpty()) {
                edit_text_login_password.error = "Mật khẩu quá ngắn!"
                return@setOnClickListener
            }

            // Checking whether user existed or not
            if (userDB.isUserExist(
                    User(
                        null,
                        edit_text_login_username.text.toString(),
                        edit_text_login_password.text.toString()
                    )
                )
            ) {
                //If existed, then open MainActivity
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()

                // Use sharedPreferences to storage username

                globalSharedPreferences.edit()
                    .putString("USERNAME", edit_text_login_username.text.toString())
                    .apply()

                localSharedPreferences.edit()
                    .putString("USERNAME", edit_text_login_username.text.toString())
                    .putString("PASSWORD", edit_text_login_password.text.toString())
                    .putBoolean("REMEMBER", checkbox_login_remember_info.isChecked)
                    .apply()

                startActivity(Intent(this, MainActivity::class.java))

                // Clear all back stack activities
                finishAffinity()

            }
            // If user does not exist, then notify that user hasn't been created yet
            else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
            }
        }

        // Wanna create new user? Click here!
        text_view_signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }


    }
}