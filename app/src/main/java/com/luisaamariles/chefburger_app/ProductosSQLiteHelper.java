package com.luisaamariles.chefburger_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luisa Maria Amariles on 18/10/2016.
 */
public class ProductosSQLiteHelper extends SQLiteOpenHelper {
    private String DATA_BASE_NAME = "ProductosBD";
    private  int DATA_VERSION = 1;

    String sqlCreate = "CREATE TABLE Productos("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "producto  TEXT,"+
            "tiempo TEXT,"+
            "descripcion  TEXT)";

    public ProductosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Productos");
        db.execSQL(sqlCreate);

    }
}
