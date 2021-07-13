package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.Outcome

interface OutcomeDao {

    fun getAllOutcomes(): ArrayList<Outcome>

    fun getOutcome(index: Int):Outcome

    fun newOutcome(outcome: Outcome):Boolean

    fun editOutcome(outcome: Outcome):Boolean

    fun removeOutcome(outcome: Outcome):Boolean
}