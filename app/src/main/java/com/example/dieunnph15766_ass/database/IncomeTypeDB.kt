package com.example.dieunnph15766_ass.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766_ass.dao.IncomeTypeDao
import com.example.dieunnph15766_ass.model.IncomeType
import java.util.*

class IncomeTypeDB(private val db: Database) : IncomeTypeDao {
    private var sqliteDatabase: SQLiteDatabase? = null
    private var list: ArrayList<IncomeType>? = null
    override fun getAllIncomeType(): ArrayList<IncomeType> {
        sqliteDatabase = db.writableDatabase
        val cursor = sqliteDatabase!!.rawQuery("SELECT * FROM " + Database.TABLE_INCOME_TYPE, null)
        list = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val incomeType = IncomeType()
                incomeType.incomeTypeID = cursor.getLong(0)
                incomeType.incomeTypeName = cursor.getString(1)
                incomeType.userName = cursor.getString(2)
                list!!.add(incomeType)
                cursor.moveToNext()
            }
        }
        cursor.close()
        sqliteDatabase!!.close()
        return list!!
    }

    override fun getIncomeType(index: Int): IncomeType {
        return list!![index]
    }

    override fun editIncomeType(incomeType: IncomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("INCOME_TYPE_ID", incomeType.incomeTypeID)
        values.put("INCOME_TYPE_NAME", incomeType.incomeTypeName)
        values.put("USERNAME", incomeType.userName)
        return sqliteDatabase!!.update(
            Database.TABLE_INCOME_TYPE,
            values,
            "INCOME_TYPE_NAME = ?",
            arrayOf(incomeType.incomeTypeName.toString() + "")
        ) > 0
    }

    override fun removeIncomeType(incomeType: IncomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        return sqliteDatabase!!.delete(
            Database.TABLE_INCOME_TYPE,
            "INCOME_TYPE_NAME = ?",
            arrayOf(incomeType.incomeTypeName.toString() + "")
        ) > 0
    }

    override fun newIncomeType(incomeType: IncomeType): Boolean {
        sqliteDatabase = db.writableDatabase
        val values = ContentValues()
        values.put("INCOME_TYPE_ID", incomeType.incomeTypeID)
        values.put("INCOME_TYPE_NAME", incomeType.incomeTypeName)
        values.put("USERNAME", incomeType.userName)
        return sqliteDatabase!!.insert(Database.TABLE_INCOME_TYPE, null, values) > 0
    }
}