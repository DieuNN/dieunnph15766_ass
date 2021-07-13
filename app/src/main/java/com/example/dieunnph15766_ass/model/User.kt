package com.example.dieunnph15766_ass.model

class User {
    var userId: Long? = null
    var username:String? = null
    var password:String? = null

    constructor(userId: Long?, username: String?, password: String?) {
        this.userId = userId
        this.username = username
        this.password = password
    }

    constructor()


}