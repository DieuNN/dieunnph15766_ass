package com.example.dieunnph15766_ass.model

class IncomeType {
    var incomeTypeID:Long? = null
    var incomeTypeName:String? = null

    constructor()
    constructor(incomeTypeID: Long?, incomeTypeName: String?) {
        this.incomeTypeID = incomeTypeID
        this.incomeTypeName = incomeTypeName
    }
}