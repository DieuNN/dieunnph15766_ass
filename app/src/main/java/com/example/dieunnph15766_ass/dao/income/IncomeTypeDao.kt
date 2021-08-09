package com.example.dieunnph15766_ass.dao.income

import com.example.dieunnph15766_ass.model.income.IncomeType

interface IncomeTypeDao {
    fun getAllIncomeType(username:String):ArrayList<IncomeType>

    fun getIncomeType(index:Int): IncomeType

    fun editIncomeType(oldValue:String, newValue:String, username: String):Boolean

    fun removeIncomeType(incomeType: IncomeType, username: String):Boolean

    fun newIncomeType(incomeType: IncomeType):Boolean

}