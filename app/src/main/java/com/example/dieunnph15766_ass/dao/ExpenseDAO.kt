package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.Expense

interface ExpenseDAO {

    fun getAllExpense(): ArrayList<Expense>

    fun getExpense(index: Int):Expense

    fun newExpense(expense: Expense):Boolean

    fun editExpense(expense: Expense):Boolean

    fun removeExpense(expense: Expense):Boolean
}