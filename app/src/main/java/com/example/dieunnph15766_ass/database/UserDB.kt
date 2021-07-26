package com.example.dieunnph15766_ass.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.UserDao
import com.example.dieunnph15766_ass.model.User
import java.util.*

class UserDB(private val db: Database) : UserDao {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var users: ArrayList<User>? = null
    override fun getAllUsers(): ArrayList<User> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_USER, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                users = ArrayList()
                val user = User()
                user.userId = cursor.getLong(0)
                user.username = cursor.getString(1)
                user.password = cursor.getString(2)
                users!!.add(user)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return users!!
    }

    override fun getUser(index: Int): User {
        return users!![index]
    }

    override fun newUser(user: User): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("USER_ID", user.userId)
        values.put("USERNAME", user.username)
        values.put("PASSWORD", user.password)
        return sqliteDatabase!!.insert(Database.TABLE_USER, null, values) > 0
    }

    override fun isUserExist(user: User): Boolean {
        sqliteDatabase = db.writableDatabase
       val cursor = sqliteDatabase!!.rawQuery("SELECT COUNT(*) FROM ${Database.TABLE_USER} WHERE USERNAME = \"${user.username}\" AND PASSWORD = \"${user.password}\" ", null)
        cursor.moveToFirst()
        return cursor.getInt(0) == 1
    }
}