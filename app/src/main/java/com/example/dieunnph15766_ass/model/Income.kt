package com.example.dieunnph15766_ass.model

class Income {
    var incomeID:Long? = null
    var incomeName:String? = null
    var incomeDate:String? = null
    var incomeTypeName:String? = null
    var userID:Long? = null
    var incomeAmount:Long? = null

    constructor(
        incomeID: Long?,
        incomeName: String?,
        incomeDate: String?,
        incomeTypeName: String?,
        userID: Long?,
        incomeAmount: Long?
    ) {
        this.incomeID = incomeID
        this.incomeName = incomeName
        this.incomeDate = incomeDate
        this.incomeTypeName = incomeTypeName
        this.userID = userID
        this.incomeAmount = incomeAmount
    }

    constructor()
}