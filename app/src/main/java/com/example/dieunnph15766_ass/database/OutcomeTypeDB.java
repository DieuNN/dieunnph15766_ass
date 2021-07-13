package com.example.dieunnph15766_ass.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dieunnph15766_ass.dao.OutcomeTypeDao;
import com.example.dieunnph15766_ass.model.OutcomeType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OutcomeTypeDB implements OutcomeTypeDao {
    private Database db;
    private SQLiteDatabase sqliteDatabase;
    private ArrayList<OutcomeType> list;

    public OutcomeTypeDB(Database db) {
        this.db = db;
    }

    @NotNull
    @Override
    public ArrayList<OutcomeType> getAllOutcomes() {
        sqliteDatabase = db.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_OUTCOME_TYPE, null);
        list = new ArrayList<OutcomeType>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                OutcomeType outcomeType = new OutcomeType();
                outcomeType.setOutcomeTypeID(cursor.getLong(0));
                outcomeType.setOutcomeTypeName(cursor.getString(1));

                list.add(outcomeType);

                cursor.moveToNext();
            }
        }

        cursor.close();
        sqliteDatabase.close();

        return list;
    }

    @NotNull
    @Override
    public OutcomeType getOutcomeType(int index) {
        return list.get(index);
    }

    @Override
    public boolean newOutcomeType(@NotNull OutcomeType outcome) {
        sqliteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("outcomeID", outcome.getOutcomeTypeID());
        values.put("outcomeName", outcome.getOutcomeTypeName());

        return sqliteDatabase.insert(Database.TABLE_OUTCOME_TYPE, null, values) > 0;
    }

    @Override
    public boolean removeOutcomeType(@NotNull OutcomeType outcome) {
        sqliteDatabase = db.getWritableDatabase();

        return sqliteDatabase.delete(Database.TABLE_OUTCOME_TYPE, "outcomeID = ?", new String[]{outcome.getOutcomeTypeID() + ""}) > 0;
    }

    @Override
    public boolean editOutcomeType(@NotNull OutcomeType outcome) {
        sqliteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("outcomeID", outcome.getOutcomeTypeID());
        values.put("outcomeName", outcome.getOutcomeTypeName());

        return sqliteDatabase.update(Database.TABLE_OUTCOME_TYPE, values, "outcomeID = ?", new String[]{outcome.getOutcomeTypeID() + ""}) > 0;
    }
}
