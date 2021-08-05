package com.example.dieunnph15766_ass.dao.user

import com.example.dieunnph15766_ass.model.user.User

interface UserDao {
    fun getAllUsers(): ArrayList<User>

    fun getUser(index: Int): User

    fun newUser(user: User): Boolean

    fun isUserExist(user: User): Boolean

}