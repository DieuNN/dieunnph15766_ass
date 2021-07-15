package com.example.dieunnph15766_ass.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.OutcomeTypeDao
import com.example.dieunnph15766_ass.model.OutcomeType
import java.util.*

class OutcomeTypeDB(private val db: Database) : OutcomeTypeDao {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<OutcomeType>? = null
    override fun getAllOutcomes(): ArrayList<OutcomeType> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_OUTCOME_TYPE, null)
        list = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val outcomeType = OutcomeType()
                outcomeType.outcomeTypeID = cursor.getLong(0)
                outcomeType.outcomeTypeName = cursor.getString(1)
                list!!.add(outcomeType)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getOutcomeType(index: Int): OutcomeType {
        return list!![index]
    }

    override fun newOutcomeType(outcome: OutcomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("OUTCOME_TYPE_ID", outcome.outcomeTypeID)
        values.put("OUTCOME_TYPE_NAME", outcome.outcomeTypeName)
        return sqliteDatabase!!.insert(Database.TABLE_OUTCOME_TYPE, null, values) > 0
    }

    override fun removeOutcomeType(outcome: OutcomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_OUTCOME_TYPE,
            "OUTCOME_TYPE_ID = ?",
            arrayOf(outcome.outcomeTypeID.toString() + "")
        ) > 0
    }

    override fun editOutcomeType(outcome: OutcomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("OUTCOME_TYPE_ID", outcome.outcomeTypeID)
        values.put("OUTCOME_TYPE_NAME", outcome.outcomeTypeName)
        return sqliteDatabase!!.update(
            Database.TABLE_OUTCOME_TYPE,
            values,
            "OUTCOME_TYPE_ID = ?",
            arrayOf(outcome.outcomeTypeID.toString() + "")
        ) > 0
    }
}