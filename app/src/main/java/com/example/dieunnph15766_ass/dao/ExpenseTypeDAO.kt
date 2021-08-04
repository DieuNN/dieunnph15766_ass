package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.ExpenseType

interface ExpenseTypeDAO {
    fun getAllExpense(): ArrayList<ExpenseType>

    fun getExpenseType(index: Int):ExpenseType

    fun newExpenseType(expense: ExpenseType):Boolean

    fun removeExpenseType(expense: ExpenseType):Boolean

    fun editExpenseType(expense: ExpenseType):Boolean
}