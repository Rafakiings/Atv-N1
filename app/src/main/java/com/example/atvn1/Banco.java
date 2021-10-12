package com.example.atvn1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class Banco extends SQLiteOpenHelper {

    private static final String Nome_Banco = "AtvN1";
    private static final int VERSAO = 1;

    public Banco(Context context){
        super(context, Nome_Banco, null, VERSAO);
    }

    @Override
    public void onCreate (@NonNull SQLiteDatabase db) {
        db.execSQL(     "CREATE TABLE IF NOT EXISTS personagens ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT  NOT NULL , " +
                " level TEXT NOT NULL, " +
                " classes TEXT NOT NULL"
        );
        db.execSQL(     "CREATE TABLE IF NOT EXISTS anotacoes ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " anot TEXT  NOT NULL "
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
