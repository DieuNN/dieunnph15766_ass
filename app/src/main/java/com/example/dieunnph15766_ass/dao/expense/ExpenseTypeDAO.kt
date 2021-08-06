package com.example.dieunnph15766_ass.dao.expense

import com.example.dieunnph15766_ass.model.expense.ExpenseType

interface ExpenseTypeDAO {
    fun getAllExpense(): ArrayList<ExpenseType>

    fun getExpenseType(index: Int): ExpenseType

    fun newExpenseType(expense: ExpenseType):Boolean

    fun removeExpenseType(expense: ExpenseType):Boolean

    fun editExpenseType(oldValue:String, newValue:String):Boolean
}