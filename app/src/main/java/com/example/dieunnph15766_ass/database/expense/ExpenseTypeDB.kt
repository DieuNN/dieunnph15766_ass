package com.example.dieunnph15766_ass.database.expense

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.example.dieunnph15766_ass.dao.expense.ExpenseTypeDAO
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.model.expense.ExpenseType
import java.util.*

class ExpenseTypeDB(private val db: Database) : ExpenseTypeDAO {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<ExpenseType>? = null
    override fun getAllExpenseType(): ArrayList<ExpenseType> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_EXPENSE_TYPE, null)
        list = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val expenseType = ExpenseType()
                expenseType.expenseID = cursor.getLong(0)
                expenseType.expenseTypeName = cursor.getString(1)
                expenseType.username = cursor.getString(2)
                list!!.add(expenseType)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getExpenseType(index: Int): ExpenseType {
        return list!![index]
    }

    override fun newExpenseType(expense: ExpenseType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("EXPENSE_TYPE_ID", expense.expenseID)
        values.put("EXPENSE_TYPE_NAME", expense.expenseTypeName)
        values.put("USERNAME", expense.username)
        return sqliteDatabase!!.insert(Database.TABLE_EXPENSE_TYPE, null, values) > 0
    }

    override fun removeExpenseType(expense: ExpenseType): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_EXPENSE_TYPE,
            "EXPENSE_TYPE_NAME = ?",
            arrayOf(expense.expenseTypeName.toString())
        ) > 0
    }

    override fun editExpenseType(oldValue: String, newValue: String): Boolean {
        sqliteDatabase = db.writableDatabase
        try {
            sqliteDatabase!!.execSQL("UPDATE EXPENSE_TYPE SET EXPENSE_TYPE_NAME =\"$newValue\" WHERE EXPENSE_TYPE_NAME = \"$oldValue\"")
            return true
        } catch (e: SQLiteException) {
            return false
        }
    }
}