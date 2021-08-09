package com.example.dieunnph15766_ass.dao.expense

import com.example.dieunnph15766_ass.model.expense.Expense

interface ExpenseDAO {

    fun getAllExpense(username:String): ArrayList<Expense>

    fun getExpense(index: Int): Expense

    fun newExpense(expense: Expense):Boolean

    fun editExpense(expense: Expense, username:String):Boolean

    fun removeExpense(expense: Expense, username: String):Boolean
}