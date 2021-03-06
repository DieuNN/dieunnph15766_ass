package com.example.dieunnph15766_ass.database.expense

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.expense.ExpenseDAO
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.model.expense.Expense
import kotlin.collections.ArrayList

class ExpenseDB(private val db: Database) : ExpenseDAO {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<Expense>? = null
    override fun getAllExpense(username: String): ArrayList<Expense> {
        sqliteDatabase = db.writableDatabase
        list = ArrayList()
        val cursor = sqliteDatabase!!.rawQuery(
            "SELECT * FROM  ${Database.TABLE_EXPENSE} WHERE USERNAME = \"${username}\" ",
            null
        )
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val expense = Expense()
                /*  "CREATE TABLE $TABLE_EXPENSE(
                EXPENSE_ID INTEGER PRIMARY KEY AUTOINCREMENT ,
                 EXPENSE_NAME TEXT ,
                 EXPENSE_DATE TEXT,
                 EXPENSE_TYPE_NAME TEXT,
                  USERNAME TEXT,
                  EXPENSE_AMOUNT REAL)" */
                expense.expenseID = cursor.getLong(0)
                expense.expenseName = cursor.getString(1)
                expense.expenseDate = cursor.getString(2)
                expense.expenseTypeName = cursor.getString(3)
                expense.userName = cursor.getString(4)
                expense.expenseAmount = cursor.getLong(5)
                expense.expenseNote = cursor.getString(6)
                list!!.add(expense)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getExpense(index: Int): Expense {
        return list!![index]
    }

    override fun newExpense(expense: Expense): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("EXPENSE_ID", expense.expenseID)
        values.put("EXPENSE_NAME", expense.expenseName)
        values.put("EXPENSE_DATE", expense.expenseDate)
        values.put("EXPENSE_TYPE_NAME", expense.expenseTypeName)
        values.put("USERNAME", expense.userName)
        values.put("EXPENSE_AMOUNT", expense.expenseAmount)
        values.put("EXPENSE_NOTE", expense.expenseNote)
        return sqliteDatabase!!.insert(Database.TABLE_EXPENSE, null, values) > 0
    }

    override fun editExpense(expense: Expense, username: String): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("EXPENSE_NAME", expense.expenseName)
        values.put("EXPENSE_DATE", expense.expenseDate)
        values.put("EXPENSE_TYPE_NAME", expense.expenseTypeName)
        values.put("USERNAME", expense.userName)
        values.put("EXPENSE_AMOUNT", expense.expenseAmount)
        values.put("EXPENSE_NOTE", expense.expenseNote)
        return sqliteDatabase!!.update(
            Database.TABLE_EXPENSE,
            values,
            "EXPENSE_NAME = ? AND USERNAME = ?",
            arrayOf(expense.expenseName.toString(), username)
        ) > 0
    }

    override fun removeExpense(expense: Expense, username: String): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_EXPENSE,
            "EXPENSE_NAME = ? AND USERNAME = ?",
            arrayOf(expense.expenseName.toString(), username)
        ) > 0
    }
}