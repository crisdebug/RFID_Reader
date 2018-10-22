package com.example.samuel.rfidreader.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "db.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "Nome";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_ENTERED = "Entrou";

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
