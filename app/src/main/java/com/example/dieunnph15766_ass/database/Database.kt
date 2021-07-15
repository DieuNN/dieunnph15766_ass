package com.example.dieunnph15766_ass.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MONEY_MANAGEMENT";
    public static final String TABLE_USER = "USER";
    public static final String TABLE_INCOME_TYPE = "INCOME_TYPE";
    public static final String TABLE_INCOME = "INCOME";
    public static final String TABLE_OUTCOME_TYPE = "OUTCOME_TYPE";
    public static final String TABLE_OUTCOME = "OUTCOME";
    public static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser = "CREATE TABLE " +  TABLE_USER + " (USER_ID INTEGER PRIMARY KEY, USERNAME TEXT, PASSWORD TEXT)";
        db.execSQL(createTableUser);

        String createTableIncomeType = "CREATE TABLE " +  TABLE_INCOME_TYPE + "(INCOME_TYPE_ID INTEGER PRIMARY KEY , INCOME_TYPE_NAME TEXT)";
        db.execSQL(createTableIncomeType);

        String createTableOutcomeType = "CREATE TABLE " + TABLE_OUTCOME_TYPE + "(OUTCOME_TYPE_ID INTEGER PRIMARY KEY , OUTCOME_TYPE_NAME TEXT)";
        db.execSQL(createTableOutcomeType);

        String createTableIncome = "CREATE TABLE " + TABLE_INCOME +  "(INCOME_ID INTEGER PRIMARY KEY , INCOME_NAME TEXT, INCOME_DATE TEXT, INCOME_TYPE_NAME TEXT, USER_ID INTEGER, INCOME_AMOUNT REAL)";
        db.execSQL(createTableIncome);

        String createTableOutcome = "CREATE TABLE " + TABLE_OUTCOME + "(OUTCOME_ID INTEGER PRIMARY KEY , OUTCOME_NAME TEXT, OUTCOME_DATE TEXT, OUTCOME_TYPE_NAME TEXT, USER_ID INTEGER, OUTCOME_AMOUNT REAL)";
        db.execSQL(createTableOutcome);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
