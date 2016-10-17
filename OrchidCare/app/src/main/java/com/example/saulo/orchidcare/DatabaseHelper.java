package com.example.saulo.orchidcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Saulo on 5/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Nombre de la base de datos
    public static final String DATABASE_NAME = "orchidCareDB.db";

    //Nombre de una de las tablas y sus columnas
    public static final String TABLE_NAME_A = "addOrchidTable";
    public static final String COLA1 = "orchidName";
    public static final String COLA2 = "orchidType";
    public static final String COLA3 = "orchidDate";

    //Nombre de una de las tablas y sus columnas
    public static final String TABLE_NAME_B = "searchTable";
    public static final String COLB1 = "orchidTypeName";
    public static final String COLB2 = "orchidDesc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_A + "(" + COLA1 + " " +
                "TEXT PRIMARY KEY, " + COLA2 + " TEXT N" +
                "OT NULL, " + COLA3 + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_NAME_B + "(" + COLB1 + " T" +
                "EXT PRIMARY KEY, " + COLB2 + " TEXT NO" +
                "T NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_B);
        onCreate(db);
    }

    public boolean insertDataToAddOrchidTable(String orchidName, String orchidType, String orchidDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLA1, orchidName);
        contentValues.put(COLA2, orchidType);
        contentValues.put(COLA3, orchidDate);
        long result = db.insert(TABLE_NAME_A, null, contentValues);
        if(result == -1)
            return false;
        else return true;
    }

    public boolean insertDataToSearchTable(String orchidTypeName, String orchidDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLB1, orchidTypeName);
        contentValues.put(COLB2, orchidDesc);
        long result = db.insert(TABLE_NAME_B, null, contentValues);
        if (result == -1)
            return false;
        else return true;
    }

    public Cursor getDataForSearchActivity() {
        SQLiteDatabase db = this.getWritableDatabase();
        String columns[] = {COLB1, COLB2};
        Cursor res = this.getReadableDatabase().query(TABLE_NAME_B, columns, null, null, null, null, null);
        return res;
    }

    public Cursor getDataForMyGroveActivity() {
        SQLiteDatabase db = this.getWritableDatabase();
        String columns[] = {COLA1, COLA2, COLA3};
        Cursor res = this.getReadableDatabase().query(TABLE_NAME_A, columns, null, null, null, null, null);
        return res;
    }
}
