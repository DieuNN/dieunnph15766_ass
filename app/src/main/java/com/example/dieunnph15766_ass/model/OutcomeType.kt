package com.example.dieunnph15766_ass.model

class OutcomeType {
    var outcomeTypeID:Long? = null
    var outcomeTypeName:String? = null

    constructor(OutcomeTypeID: Long?, OutcomeTypeName: String?) {
        this.outcomeTypeID = OutcomeTypeID
        this.outcomeTypeName = OutcomeTypeName
    }

    constructor()
}