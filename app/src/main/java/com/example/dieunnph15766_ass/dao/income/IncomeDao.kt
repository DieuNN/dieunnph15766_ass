package com.example.dieunnph15766_ass.dao.income

import com.example.dieunnph15766_ass.model.income.Income

interface IncomeDao {
    fun getAllIncomes(username: String):ArrayList<Income>

    fun getIncome(index: Int): Income

    fun newIncome(income: Income):Boolean

    fun editIncome(income: Income, username: String):Boolean

    fun removeIncome(income: Income, username: String):Boolean
}