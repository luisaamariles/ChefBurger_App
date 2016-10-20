package com.luisaamariles.chefburger_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Luisa Maria Amariles on 19/10/2016.
 */
public class EliminarActivity extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase dbFavoritos, dbProductos;
    String desc, prod, borrar, nombre,prod2;
    Button beliminar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.eliminar);

        FavoritosSQLiteHelper favoritos = new FavoritosSQLiteHelper(this, "FavoritosBD", null, 1);
        dbFavoritos = favoritos.getWritableDatabase();
        ProductosSQLiteHelper productos= new ProductosSQLiteHelper(this,"ProductosBD",null,1);
        dbProductos = productos.getWritableDatabase();
        Bundle extras = getIntent().getExtras();
        desc = extras.getString("Name1");
        nombre =extras.getString("Name2");
        beliminar =(Button) findViewById(R.id.eliminar2);
        beliminar.setOnClickListener(this);
        Cursor c = dbFavoritos.rawQuery("select * from favoritos where usuario='"+ nombre + "' and producto='"+desc+"'",null);
        //Cursor c1 = dbFavoritos.rawQuery("select * from favoritos where producto='"+ desc + "'",null);
        if(c.moveToFirst()) {
            do {
                prod = c.getString(2);
               // prod2= c1.getString(1);
                //Toast.makeText(EliminarActivity.this, "producto "+ prod, Toast.LENGTH_LONG).show();

                if (prod.equals(desc)) {
                    borrar=c.getString(0);

                    //Toast.makeText(EliminarActivity.this,"id "+borrar+" nom"+nombre, Toast.LENGTH_LONG).show();
                }
            } while (c.moveToNext());
        }
    }
    public void onClick(View v) {
        Toast.makeText(EliminarActivity.this,"Eliminado!", Toast.LENGTH_LONG).show();
        dbFavoritos.delete("Favoritos","id='"+borrar+"'",null);
        Intent intent = new Intent(this, MiperfilActivity.class);
        startActivity(intent);
    }
}


