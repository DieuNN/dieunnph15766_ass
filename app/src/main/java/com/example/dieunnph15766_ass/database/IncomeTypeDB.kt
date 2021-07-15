package com.example.dieunnph15766_ass.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dieunnph15766_ass.dao.IncomeTypeDao;
import com.example.dieunnph15766_ass.model.IncomeType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IncomeTypeDB implements IncomeTypeDao {
    private Database db;
    private SQLiteDatabase sqliteDatabase;
    private ArrayList<IncomeType> list;

    public IncomeTypeDB(Database db) {
        this.db = db;
    }


    @NotNull
    @Override
    public ArrayList<IncomeType> getAllIncomeType() {
        sqliteDatabase = db.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_INCOME_TYPE, null);
        list = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {
                IncomeType incomeType = new  IncomeType();

                incomeType.setIncomeTypeID(cursor.getLong(0));
                incomeType.setIncomeTypeName(cursor.getString(1));

                list.add(incomeType);

                cursor.moveToNext();
            }

        }
        cursor.close();
        sqliteDatabase.close();
        return list;
    }

    @NotNull
    @Override
    public IncomeType getIncomeType(int index) {
        return list.get(index);
    }

    @Override
    public boolean editIncomeType(@NotNull IncomeType incomeType) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("INCOME_TYPE_ID", incomeType.getIncomeTypeID());
        values.put("INCOME_TYPE_NAME", incomeType.getIncomeTypeName());

        return sqliteDatabase.update(Database.TABLE_INCOME_TYPE, values, "INCOME_TYPE_ID = ?", new String[]{incomeType.getIncomeTypeID() +""}) > 0;
    }

    @Override
    public boolean removeIncomeType(@NotNull IncomeType incomeType) {
        sqliteDatabase = db.getWritableDatabase();
        return sqliteDatabase.delete(Database.TABLE_INCOME_TYPE, "INCOME_TYPE_ID = ?", new String[]{incomeType.getIncomeTypeID() + ""}) > 0;
    }

    @Override
    public boolean newIncomeType(@NotNull IncomeType incomeType) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("INCOME_TYPE_ID", incomeType.getIncomeTypeID());
        values.put("INCOME_TYPE_NAME", incomeType.getIncomeTypeName());

        return sqliteDatabase.insert(Database.TABLE_INCOME_TYPE, null, values) > 0;
    }
}
