package com.example.stocksakanabento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
//    Nama Tabel
    public static final String TABLE_NAME = "tbl_food";

//    Nama Kolom dalam tabel
    public static final String _ID = "_id";
    public static final String PRICE = "price";
    public static final String FOOD = "food";

    //    Nama Database
    static final String DB_NAME = "SakanaBento.DB";

    //    Versi Database
    static final int DB_VERSION = 1;

    //    membuat query tabel
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRICE + " TEXT NOT NULL, " + FOOD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
