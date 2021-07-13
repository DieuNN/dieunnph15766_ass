package com.example.dieunnph15766_ass.dao

import com.example.dieunnph15766_ass.model.Outcome
import com.example.dieunnph15766_ass.model.OutcomeType

interface OutcomeTypeDao {
    fun getAllOutcomes(): ArrayList<OutcomeType>

    fun getOutcomeType(index: Int):OutcomeType

    fun newOutcomeType(outcome: OutcomeType):Boolean

    fun removeOutcomeType(outcome: OutcomeType):Boolean

    fun editOutcomeType(outcome: OutcomeType):Boolean
}