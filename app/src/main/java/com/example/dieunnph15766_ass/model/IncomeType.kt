package com.example.dieunnph15766_ass.model

class IncomeType {
    var incomeTypeID:Long? = null
    var incomeTypeName:String? = null
    var userName:String? = null

    constructor()
    constructor(incomeTypeID: Long?, incomeTypeName: String?, userName: String?) {
        this.incomeTypeID = incomeTypeID
        this.incomeTypeName = incomeTypeName
        this.userName = userName
    }
}