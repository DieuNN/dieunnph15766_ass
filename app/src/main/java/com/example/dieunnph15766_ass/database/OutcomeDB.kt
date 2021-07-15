package com.example.dieunnph15766_ass.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.OutcomeDao
import com.example.dieunnph15766_ass.model.Outcome
import java.util.*

class OutcomeDB(private val db: Database) : OutcomeDao {
    private var sqliteDatabase: SQLiteDatabase? = null
    private val list: ArrayList<Outcome>? = null
    override fun getAllOutcomes(): ArrayList<Outcome> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_OUTCOME, null)
        if (cursor.count > 0) {
            while (!cursor.isAfterLast) {
                val outcome = Outcome()
                outcome.outcomeID = cursor.getLong(0)
                outcome.outcomeName = cursor.getString(1)
                outcome.outcomeDate = cursor.getString(2)
                outcome.outcomeTypeName = cursor.getString(3)
                outcome.userID = cursor.getLong(4)
                outcome.outcomeAmount = cursor.getLong(5)
                list!!.add(outcome)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getOutcome(index: Int): Outcome {
        return list!![index]
    }

    override fun newOutcome(outcome: Outcome): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("OUTCOME_ID", outcome.outcomeID)
        values.put("OUTCOME_NAME", outcome.outcomeName)
        values.put("OUTCOME_DATE", outcome.outcomeDate)
        values.put("OUTCOME_TYPE_NAME", outcome.outcomeTypeName)
        values.put("USER_ID", outcome.userID)
        values.put("OUTCOME_AMOUNT", outcome.outcomeAmount)
        return sqliteDatabase!!.insert(Database.TABLE_OUTCOME, null, values) > 0
    }

    override fun editOutcome(outcome: Outcome): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("OUTCOME_ID", outcome.outcomeID)
        values.put("OUTCOME_NAME", outcome.outcomeName)
        values.put("OUTCOME_DATE", outcome.outcomeDate)
        values.put("OUTCOME_TYPE_NAME", outcome.outcomeTypeName)
        values.put("USER_ID", outcome.userID)
        values.put("OUTCOME_AMOUNT", outcome.outcomeAmount)
        return sqliteDatabase!!.update(
            Database.TABLE_OUTCOME,
            values,
            "OUTCOME_ID = ?",
            arrayOf(outcome.outcomeID.toString() + "")
        ) > 0
    }

    override fun removeOutcome(outcome: Outcome): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_OUTCOME,
            "OUTCOME_ID = ?",
            arrayOf(outcome.outcomeID.toString() + "")
        ) > 0
    }
}