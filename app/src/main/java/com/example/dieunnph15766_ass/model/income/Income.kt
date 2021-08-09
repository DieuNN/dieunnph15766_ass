package com.example.dieunnph15766_ass.model.income

class Income {
    var incomeID:Long? = null
    var incomeName:String? = null
    var incomeDate:String? = null
    var incomeTypeName:String? = null
    var userName:String? = null
    var incomeAmount:Long? = null
    var incomeNote:String? = null

    constructor(
        incomeID: Long?,
        incomeName: String?,
        incomeDate: String?,
        incomeTypeName: String?,
        userName: String?,
        incomeAmount: Long?,
        incomeNote: String?,
    ) {
        this.incomeID = incomeID
        this.incomeName = incomeName
        this.incomeDate = incomeDate
        this.incomeTypeName = incomeTypeName
        this.userName = userName
        this.incomeAmount = incomeAmount
        this.incomeNote = incomeNote
    }

    constructor()
}