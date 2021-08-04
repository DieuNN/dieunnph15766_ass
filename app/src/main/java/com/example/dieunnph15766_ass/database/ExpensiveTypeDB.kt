package com.example.dieunnph15766_ass.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.ExpenseTypeDAO
import com.example.dieunnph15766_ass.model.ExpenseType
import java.util.*

class ExpensiveTypeDB(private val db: Database) : ExpenseTypeDAO {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<ExpenseType>? = null
    override fun getAllExpense(): ArrayList<ExpenseType> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_EXPENSE_TYPE, null)
        list = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val expenseType = ExpenseType()
                expenseType.expenseID = cursor.getLong(0)
                expenseType.expenseName = cursor.getString(1)
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
        values.put("OUTCOME_TYPE_ID", expense.expenseID)
        values.put("OUTCOME_TYPE_NAME", expense.expenseName)
        return sqliteDatabase!!.insert(Database.TABLE_EXPENSE_TYPE, null, values) > 0
    }

    override fun removeExpenseType(expense: ExpenseType): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_EXPENSE_TYPE,
            "OUTCOME_TYPE_ID = ?",
            arrayOf(expense.expenseID.toString() + "")
        ) > 0
    }

    override fun editExpenseType(expense: ExpenseType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("OUTCOME_TYPE_ID", expense.expenseID)
        values.put("OUTCOME_TYPE_NAME", expense.expenseName)
        return sqliteDatabase!!.update(
            Database.TABLE_EXPENSE_TYPE,
            values,
            "OUTCOME_TYPE_ID = ?",
            arrayOf(expense.expenseID.toString() + "")
        ) > 0
    }
}