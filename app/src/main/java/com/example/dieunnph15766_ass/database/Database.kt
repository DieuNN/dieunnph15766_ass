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
            "CREATE TABLE $TABLE_INCOME_TYPE(INCOME_TYPE_ID INTEGER PRIMARY KEY AUTOINCREMENT , INCOME_TYPE_NAME TEXT)"
        db.execSQL(createTableIncomeType)
        val createTableOutcomeType =
            "CREATE TABLE $TABLE_OUTCOME_TYPE(OUTCOME_TYPE_ID INTEGER PRIMARY KEY AUTOINCREMENT , OUTCOME_TYPE_NAME TEXT)"
        db.execSQL(createTableOutcomeType)
        val createTableIncome =
            "CREATE TABLE $TABLE_INCOME(INCOME_ID INTEGER PRIMARY KEY AUTOINCREMENT , INCOME_NAME TEXT, INCOME_DATE TEXT, INCOME_TYPE_NAME TEXT, INCOME_TYPE_ID INTEGER ,  USER_ID INTEGER, INCOME_AMOUNT REAL, FOREIGN KEY (USER_ID) REFERENCES $TABLE_USER(USER_ID))"
        db.execSQL(createTableIncome)
        val createTableOutcome =
            "CREATE TABLE $TABLE_OUTCOME(OUTCOME_ID INTEGER PRIMARY KEY AUTOINCREMENT , OUTCOME_NAME TEXT, OUTCOME_DATE TEXT, OUTCOME_TYPE_NAME TEXT,INCOME_TYPE_ID INTEGER , USER_ID INTEGER, OUTCOME_AMOUNT REAL, FOREIGN KEY (USER_ID) REFERENCES $TABLE_USER(USER_ID))"
        db.execSQL(createTableOutcome)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        const val DATABASE_NAME = "MONEY_MANAGEMENT"
        const val TABLE_USER = "USER"
        const val TABLE_INCOME_TYPE = "INCOME_TYPE"
        const val TABLE_INCOME = "INCOME"
        const val TABLE_OUTCOME_TYPE = "OUTCOME_TYPE"
        const val TABLE_OUTCOME = "OUTCOME"
        const val DATABASE_VERSION = 1
    }
}