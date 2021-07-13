package com.example.dieunnph15766_ass.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dieunnph15766_ass.dao.UserDao;
import com.example.dieunnph15766_ass.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserDB implements UserDao {
    private Database db;
    private SQLiteDatabase sqliteDatabase;
    private ArrayList<User> users;

    public UserDB(Database db) {
        this.db = db;
    }

    @NotNull
    @Override
    public ArrayList<User> getAllUsers() {
        sqliteDatabase = db.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_USER, null);

        if(cursor.getCount()>0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                users = new ArrayList<User>();
                User user = new User();

                user.setUserId(cursor.getLong(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqliteDatabase.close();

        return users;
    }

    @NotNull
    @Override
    public User getUser(@NotNull int index) {
        return users.get(index);
    }

    @Override
    public boolean newUser(@NotNull User user) {
        sqliteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("userID", user.getUserId());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());

        return sqliteDatabase.insert(Database.TABLE_USER, null, values) >0;
    }
}
