package com.example.dieunnph15766_ass.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dieunnph15766_ass.dao.IncomeDao;
import com.example.dieunnph15766_ass.model.Income;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IncomeDB implements IncomeDao {
    private Database db;
    private SQLiteDatabase sqliteDatabase;
    private ArrayList<Income> list;

    public IncomeDB(Database db) {
        this.db = db;
    }



    @NotNull
    @Override
    public ArrayList<Income> getAllIncomes() {
        sqliteDatabase = db.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_INCOME, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Income income = new  Income();
                income.setIncomeID(cursor.getLong(0));
                income.setIncomeName(cursor.getString(1));
                income.setIncomeDate(cursor.getString(2));
                income.setIncomeTypeName(cursor.getString(3));
                income.setUserID(cursor.getLong(4));
                income.setIncomeAmount(cursor.getLong(5));

                list.add(income);
                cursor.moveToNext();
            }
        }

        cursor.close();
        sqliteDatabase.close();

        return list;
    }

    @NotNull
    @Override
    public Income getIncome(@NotNull int index) {
        return list.get(index);
    }

    @Override
    public boolean newIncome(@NotNull Income income) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("incomeID", income.getIncomeID());
        values.put("incomeName", income.getIncomeName());
        values.put("incomeDate", income.getIncomeDate());
        values.put("incomeTypeName", income.getIncomeTypeName());
        values.put("userID", income.getUserID());
        values.put("incomeAmount", income.getIncomeAmount());

        return sqliteDatabase.insert(Database.TABLE_INCOME, null, values) >0;
    }

    @Override
    public boolean editIncome(@NotNull Income income) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("incomeID", income.getIncomeID());
        values.put("incomeName", income.getIncomeName());
        values.put("incomeDate", income.getIncomeDate());
        values.put("incomeTypeName", income.getIncomeTypeName());
        values.put("userID", income.getUserID());
        values.put("incomeAmount", income.getIncomeAmount());

        return sqliteDatabase.update(Database.TABLE_INCOME, values, "incomeID = ?", new String[]{""+income.getIncomeID()}) > 0;
    }

    @Override
    public boolean removeIncome(@NotNull Income income) {
        sqliteDatabase = db.getWritableDatabase();
        return sqliteDatabase.delete(Database.TABLE_INCOME, "incomeID = ?", new String[]{"" + income.getIncomeID()}) > 0;
    }
}
