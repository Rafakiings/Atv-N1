package com.example.atvn1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    public static void inserir(Context context, PersonagemInfo personagemInfo){

        ContentValues valores = new ContentValues();
        valores.put("nome", personagemInfo.getNome() );
        valores.put("level", personagemInfo.getLevel() );
        valores.put("classe", personagemInfo.getClasses() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("personagens", null, valores);
    }

    public static void editar(Context context, PersonagemInfo personagemInfo){
        ContentValues valores = new ContentValues();
        valores.put("nome", personagemInfo.getNome() );
        valores.put("level", personagemInfo.getLevel() );
        valores.put("classe", personagemInfo.getClasses() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("personagens", valores, " id = " + personagemInfo.getId(), null);
    }

    public static void excluir(Context context, int idPersonagem){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("personagens", " id = " + idPersonagem , null);
    }

    public static List<PersonagemInfo> getPersonagemInfo(Context context){
        List<PersonagemInfo> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM personagens ORDER BY nome ", null );

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                PersonagemInfo pers = new PersonagemInfo("Lista Vazia ", "");
                pers.setId( cursor.getInt( 0 ) );
                pers.setNome( cursor.getString(1) );
                pers.setLevel( cursor.getString(2) );
                pers.setClasses( cursor.getString(3) );
                lista.add( pers );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }


    public static PersonagemInfo getPersonagemInfoById(Context context, int idPersonagem){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM fichas WHERE id =  " + idPersonagem, null );

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            PersonagemInfo pers = new PersonagemInfo("Lista Vazia ", "");
            pers.setId( cursor.getInt( 0 ) );
            pers.setNome( cursor.getString(1) );
            pers.setLevel( cursor.getString(2) );
            pers.setClasses( cursor.getString(3) );

            return pers;
        }else {
            return null;
        }
    }

}
