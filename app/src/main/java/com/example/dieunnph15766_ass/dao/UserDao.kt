package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.User

interface UserDao {
    fun getAllUsers(): ArrayList<User>

    fun getUser(index: Int): User

    fun newUser(user:User): Boolean
}