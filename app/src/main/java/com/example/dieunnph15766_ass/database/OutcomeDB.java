package com.example.dieunnph15766_ass.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dieunnph15766_ass.dao.OutcomeDao;
import com.example.dieunnph15766_ass.model.Outcome;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OutcomeDB implements OutcomeDao {
    private Database db;
    private SQLiteDatabase sqliteDatabase;
    private ArrayList<Outcome> list;

    public OutcomeDB(Database db) {
        this.db = db;
    }


    @NotNull
    @Override
    public ArrayList<Outcome> getAllOutcomes() {
        sqliteDatabase = db.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_OUTCOME, null);

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Outcome outcome = new Outcome();

                outcome.setOutcomeID(cursor.getLong(0));
                outcome.setOutcomeName(cursor.getString(1));
                outcome.setOutcomeDate(cursor.getString(2));
                outcome.setOutcomeTypeName(cursor.getString(3));
                outcome.setUserID(cursor.getLong(4));
                outcome.setOutcomeAmount(cursor.getLong(5));

                list.add(outcome);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqliteDatabase.close();

        return list;
    }
    @NotNull
    @Override
    public Outcome getOutcome(@NotNull int index) {
        return list.get(index);
    }

    @Override
    public boolean newOutcome(@NotNull Outcome outcome) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("OUTCOME_ID", outcome.getOutcomeID());
        values.put("OUTCOME_NAME", outcome.getOutcomeName());
        values.put("OUTCOME_DATE", outcome.getOutcomeDate());
        values.put("OUTCOME_TYPE_NAME", outcome.getOutcomeTypeName());
        values.put("USER_ID", outcome.getUserID());
        values.put("OUTCOME_AMOUNT", outcome.getOutcomeAmount());

        return sqliteDatabase.insert(Database.TABLE_OUTCOME, null,values) > 0;

    }

    @Override
    public boolean editOutcome(@NotNull Outcome outcome) {
        sqliteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("OUTCOME_ID", outcome.getOutcomeID());
        values.put("OUTCOME_NAME", outcome.getOutcomeName());
        values.put("OUTCOME_DATE", outcome.getOutcomeDate());
        values.put("OUTCOME_TYPE_NAME", outcome.getOutcomeTypeName());
        values.put("USER_ID", outcome.getUserID());
        values.put("OUTCOME_AMOUNT", outcome.getOutcomeAmount());

        return sqliteDatabase.update(Database.TABLE_OUTCOME, values, "outcomeID = ?", new String[] { outcome.getOutcomeID() +""}) > 0;
    }

    @Override
    public boolean removeOutcome(@NotNull Outcome outcome) {
        sqliteDatabase = db.getWritableDatabase();
        return sqliteDatabase.delete(Database.TABLE_OUTCOME, "outcomeID = ?", new String[] { outcome.getOutcomeID() + ""}) > 0;
    }
}
