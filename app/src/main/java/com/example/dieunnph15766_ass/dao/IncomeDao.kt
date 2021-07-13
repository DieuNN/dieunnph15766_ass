package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.Income

interface IncomeDao {
    fun getAllIncomes():ArrayList<Income>

    fun getIncome(index: Int):Income

    fun newIncome(income: Income):Boolean

    fun editIncome(income: Income):Boolean

    fun removeIncome(income: Income):Boolean
}