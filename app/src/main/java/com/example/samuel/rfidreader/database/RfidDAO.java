package com.example.samuel.rfidreader.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.samuel.rfidreader.Funcionario;

public class RfidDAO {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static RfidDAO instance;

    private RfidDAO(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static RfidDAO getInstance(Context context){
        if(instance == null){
            instance = new RfidDAO(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(this.database != null){
            this.database.close();
        }
    }

    public Funcionario getFuncionario(String code){
        Funcionario funcionario = new Funcionario();

        Cursor cursor = database.rawQuery("SELECT * FROM Funcionario WHERE code = ?", new String[]{code});
        if(cursor.moveToFirst()){
            funcionario.setId(cursor.getLong(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_ID)));
            funcionario.setCode(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_CODE)));
            funcionario.setEntrou(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_ENTERED)) == 1);
            funcionario.setNome(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_NAME)));
        }else{
            funcionario = null;
        }
        return funcionario;
    }


}
