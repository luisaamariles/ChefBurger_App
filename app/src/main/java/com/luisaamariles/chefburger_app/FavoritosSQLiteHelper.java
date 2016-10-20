package com.luisaamariles.chefburger_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luisa Maria Amariles on 17/10/2016.
 */
public class FavoritosSQLiteHelper extends SQLiteOpenHelper {
    private String DATA_BASE_NAME = "FavoritosBD";
    private  int DATA_VERSION = 1;

    String sqlCreate = "CREATE TABLE Favoritos ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "usuario   TEXT,"+
            "producto TEXT)";

    public FavoritosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Favoritos");
        db.execSQL(sqlCreate);

    }
}
