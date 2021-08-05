package com.example.dieunnph15766_ass.database.expense

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.expense.ExpenseDAO
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.model.expense.Expense
import java.util.*
import kotlin.collections.ArrayList

class ExpenseDB(private val db: Database) : ExpenseDAO {
    private var sqliteDatabase: SQLiteDatabase? = null
    private val list: ArrayList<Expense>? = null
    override fun getAllExpense(): ArrayList<Expense> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_EXPENSE, null)
        if (cursor.count > 0) {
            while (!cursor.isAfterLast) {
                val expense = Expense()
                expense.expenseID = cursor.getLong(0)
                expense.expenseName = cursor.getString(1)
                expense.expenseDate = cursor.getString(2)
                expense.expenseTypeName = cursor.getString(3)
                expense.userName = cursor.getString(4)
                expense.expenseAmount = cursor.getLong(5)
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
        return sqliteDatabase!!.insert(Database.TABLE_EXPENSE, null, values) > 0
    }

    override fun editExpense(expense: Expense): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("EXPENSE_ID", expense.expenseID)
        values.put("EXPENSE_NAME", expense.expenseName)
        values.put("EXPENSE_DATE", expense.expenseDate)
        values.put("EXPENSE_TYPE_NAME", expense.expenseTypeName)
        values.put("USER_ID", expense.userName)
        values.put("EXPENSE_AMOUNT", expense.expenseAmount)
        return sqliteDatabase!!.update(
            Database.TABLE_EXPENSE,
            values,
            "EXPENSE_ID = ?",
            arrayOf(expense.expenseID.toString() + "")
        ) > 0
    }

    override fun removeExpense(expense: Expense): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_EXPENSE,
            "EXPENSE_ID = ?",
            arrayOf(expense.expenseID.toString() + "")
        ) > 0
    }
}