package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.IncomeType

interface IncomeTypeDao {
    fun getAllIncomeType():ArrayList<IncomeType>

    fun getIncomeType(index:Int):IncomeType

    fun editIncomeType(incomeType: IncomeType):Boolean

    fun removeIncomeType(incomeType: IncomeType):Boolean

    fun newIncomeType(incomeType: IncomeType):Boolean

}