package com.example.dieunnph15766_ass.model.expense

class Expense {
    var expenseID:Long? = null
    var expenseName:String? = null
    var expenseDate:String? = null
    var expenseTypeName:String? = null
    var userName:String? = null
    var expenseAmount:Long? = null
    var expenseNote:String? = null

    constructor(
         expenseID:Long?,
         expenseName:String? ,
         expenseDate:String?,
         expenseTypeName:String?,
         userName:String? ,
         expenseAmount:Long? ,
         expenseNote: String?,
    ) {
        this.expenseID = expenseID
        this.expenseName = expenseName
        this.expenseDate = expenseDate
        this.expenseTypeName = expenseTypeName
        this.userName = userName
        this.expenseAmount = expenseAmount
        this.expenseNote = expenseNote
    }

    constructor()


}