package com.example.dieunnph15766_ass.database.income

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.income.IncomeDao
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.model.income.Income
import java.util.*

class IncomeDB(private val db: Database) : IncomeDao {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<Income>? = null

    override fun getAllIncomes(username: String): ArrayList<Income> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM ${Database.TABLE_INCOME} WHERE USERNAME = \"${username}\" " , null)
        list = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val income = Income()
                income.incomeID = cursor.getLong(0)
                income.incomeName = cursor.getString(1)
                income.incomeDate = cursor.getString(2)
                income.incomeTypeName = cursor.getString(3)
                income.userName = cursor.getString(4)
                income.incomeAmount = cursor.getLong(5)
                income.incomeNote = cursor.getString(6)
                list!!.add(income)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getIncome(index: Int): Income {
        return list!![index]
    }

    override fun newIncome(income: Income): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("INCOME_ID", income.incomeID)
        values.put("INCOME_NAME", income.incomeName)
        values.put("INCOME_DATE", income.incomeDate)
        values.put("INCOME_TYPE_NAME", income.incomeTypeName)
        values.put("USERNAME", income.userName)
        values.put("INCOME_AMOUNT", income.incomeAmount)
        values.put("INCOME_NOTE", income.incomeNote)
        return sqliteDatabase!!.insert(Database.TABLE_INCOME, null, values) > 0
    }

    override fun editIncome(income: Income, username: String): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("INCOME_NAME", income.incomeName)
        values.put("INCOME_DATE", income.incomeDate)
        values.put("INCOME_TYPE_NAME", income.incomeTypeName)
        values.put("USERNAME", income.userName)
        values.put("INCOME_AMOUNT", income.incomeAmount)
        values.put("INCOME_NOTE", income.incomeNote)
        return sqliteDatabase!!.update(
            Database.TABLE_INCOME,
            values,
            "INCOME_NAME = ? AND USERNAME = ?",
            arrayOf(income.incomeName, username)
        ) > 0
    }

    override fun removeIncome(income: Income, username: String): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_INCOME,
            "INCOME_NAME = ? AND USERNAME = ?",
            arrayOf(income.incomeName, username)
        ) > 0
    }
}