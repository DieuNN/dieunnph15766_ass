package com.example.dieunnph15766_ass.model

class Outcome {
    var outcomeID:Long? = null
    var outcomeName:String? = null
    var outcomeDate:String? = null
    var outcomeTypeName:String? = null
    var userID:Long? = null
    var outcomeAmount:Long? = null

    constructor(
        outcomeID: Long?,
        outcomeName: String?,
        outcomeDate: String?,
        incomeTypeName: String?,
        userID: Long?,
        outcomeAmount: Long?
    ) {
        this.outcomeID = outcomeID
        this.outcomeName = outcomeName
        this.outcomeDate = outcomeDate
        this.outcomeTypeName = incomeTypeName
        this.userID = userID
        this.outcomeAmount = outcomeAmount
    }

    constructor()


}