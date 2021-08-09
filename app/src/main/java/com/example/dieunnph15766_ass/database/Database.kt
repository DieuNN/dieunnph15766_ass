package com.example.dieunnph15766_ass.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTableUser =
            "CREATE TABLE $TABLE_USER (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE, PASSWORD TEXT)"
        db.execSQL(createTableUser)
        val createTableIncomeType =
            "CREATE TABLE $TABLE_INCOME_TYPE(INCOME_TYPE_ID INTEGER PRIMARY KEY AUTOINCREMENT , INCOME_TYPE_NAME TEXT UNIQUE, USERNAME TEXT)"
        db.execSQL(createTableIncomeType)
        val createTableEXPENSEType =
            "CREATE TABLE $TABLE_EXPENSE_TYPE(EXPENSE_TYPE_ID INTEGER PRIMARY KEY AUTOINCREMENT , EXPENSE_TYPE_NAME TEXT UNIQUE, USERNAME TEXT)"
        db.execSQL(createTableEXPENSEType)
        val createTableIncome =
            "CREATE TABLE $TABLE_INCOME(INCOME_ID INTEGER PRIMARY KEY AUTOINCREMENT , INCOME_NAME TEXT, INCOME_DATE TEXT, INCOME_TYPE_NAME TEXT ,  USERNAME TEXT, INCOME_AMOUNT REAL, INCOME_NOTE TEXT)"
        db.execSQL(createTableIncome)
        val createTableEXPENSE =
            "CREATE TABLE $TABLE_EXPENSE(EXPENSE_ID INTEGER PRIMARY KEY AUTOINCREMENT , EXPENSE_NAME TEXT , EXPENSE_DATE TEXT, EXPENSE_TYPE_NAME TEXT, USERNAME TEXT, EXPENSE_AMOUNT REAL, EXPENSE_NOTE TEXT)"
        db.execSQL(createTableEXPENSE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    // It's like final in Java
    companion object {
        const val DATABASE_NAME = "MONEY_MANAGEMENT"
        const val TABLE_USER = "USER"
        const val TABLE_INCOME_TYPE = "INCOME_TYPE"
        const val TABLE_INCOME = "INCOME"
        const val TABLE_EXPENSE_TYPE = "EXPENSE_TYPE"
        const val TABLE_EXPENSE = "EXPENSE"
        const val DATABASE_VERSION = 1
    }
}