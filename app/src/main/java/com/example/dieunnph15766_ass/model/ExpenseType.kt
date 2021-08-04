package com.example.dieunnph15766_ass.model

class ExpenseType {
    var expenseID:Long? = null
    var expenseName:String? = null

    constructor(OutcomeTypeID: Long?, OutcomeTypeName: String?) {
        this.expenseID = OutcomeTypeID
        this.expenseName = OutcomeTypeName
    }

    constructor()
}