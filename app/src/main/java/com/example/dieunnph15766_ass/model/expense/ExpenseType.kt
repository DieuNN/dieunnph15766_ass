package com.example.dieunnph15766_ass.model.expense

class ExpenseType {
    var expenseID:Long? = null
    var expenseName:String? = null
    var username:String? = null

    constructor(expenseID: Long?, expenseName: String?, username: String?) {
        this.expenseID = expenseID
        this.expenseName = expenseName
        this.username = username
    }

    constructor()
}