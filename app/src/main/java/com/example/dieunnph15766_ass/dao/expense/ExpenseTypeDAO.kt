package com.example.dieunnph15766_ass.dao.expense

import com.example.dieunnph15766_ass.model.expense.ExpenseType

interface ExpenseTypeDAO {
    fun getAllExpenseType(username: String): ArrayList<ExpenseType>

    fun getExpenseType(index: Int): ExpenseType

    fun newExpenseType(expense: ExpenseType):Boolean

    fun removeExpenseType(expense: ExpenseType, username:String):Boolean

    fun editExpenseType(oldValue:String, newValue:String, username: String):Boolean
}