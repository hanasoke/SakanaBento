package com.example.stocksakanabento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Query insert data
    public void insert(String price, String food) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.PRICE, price);
        contentValue.put(DatabaseHelper.FOOD, food);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Query ambil/read data
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.PRICE, DatabaseHelper.FOOD };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Query update data
    public int update(long _id, String price, String food) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PRICE, price);
        contentValues.put(DatabaseHelper.FOOD, food);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    // Query delete data
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
