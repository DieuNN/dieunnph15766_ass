package com.example.dieunnph15766_ass.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.*
import com.example.dieunnph15766_ass.model.*

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //using Database Inspector to check whether I created database

        val database = Database(this)
        val userDB = UserDB(database)
        val incomeDB = IncomeDB(database)
        val outcomeDB = OutcomeDB(database)
        val incomeTypeDB = IncomeTypeDB(database)
        val outcomeTypeDB = OutcomeTypeDB(database)

        var user = User(123, "Dieu", "Dep trai")
        userDB.newUser(user)
        userDB.getAllUsers()


        var income = Income(123, "income name", "income date", "income type name", 123, 100)
        incomeDB.newIncome(income)



        var outcome = Outcome(123, "income name", "income date", "income type name", 123, 100)
        outcomeDB.newOutcome(outcome)


        var incomeType = IncomeType(123, "outcome type")
        incomeTypeDB.newIncomeType(incomeType)



        var outcomeType  = OutcomeType(123, "outcome type")
        outcomeTypeDB.newOutcomeType(outcomeType)

        //database created successfully, use Database Inspector to check

    }
}