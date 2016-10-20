package com.luisaamariles.chefburger_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
public class PromfinActivity extends AppCompatActivity implements View.OnClickListener{
    String Nombre,Contrasena, Mail;
    String Nombre1, Fecha, desc, prod, ban, borrar;
    TextView Nomb, fec, des;
    Button bvolver, bagregar, beliminar;
    SQLiteDatabase dbFavoritos;
    ContentValues dataBD;
    SQLiteDatabase dbProductos;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promfin);

        FavoritosSQLiteHelper favoritos= new FavoritosSQLiteHelper(this,"FavoritosBD",null,1);
        dbFavoritos = favoritos.getWritableDatabase();


        ProductosSQLiteHelper productos= new ProductosSQLiteHelper(this,"ProductosBD",null,1);
        dbProductos = productos.getWritableDatabase();

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");


        Nomb = (TextView) findViewById(R.id.nombre);
        fec = (TextView) findViewById(R.id.fechap);
        bvolver = (Button) findViewById(R.id.volver);
        bagregar = (Button) findViewById(R.id.agregar);
        beliminar =(Button) findViewById(R.id.eliminar);
        bvolver.setOnClickListener(this);
        bagregar.setOnClickListener(this);
        beliminar.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        Nombre1 = extras.getString("Name1");
        Nomb.setText(Nombre1);
        Fecha = extras.getString("fecha");
        fec.setText(Fecha);

        mostrar();
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = dbFavoritos.rawQuery("select * from favoritos where usuario='"+ Nombre + "' and producto='"+Nombre1+"'",null);

        ban="";
        if(c.moveToFirst()){
            do {
                prod = c.getString(2);
                //Toast.makeText(PromfinActivity.this,Nombre1, Toast.LENGTH_LONG).show();
                if(prod.equals(Nombre1)) {
                    Toast.makeText(PromfinActivity.this, "Ya es favorito", Toast.LENGTH_LONG).show();
                    ban = "favorito";
                    borrar= c.getString(0);
                }
            } while (c.moveToNext());
        }


    }

    private void mostrar(){

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        Cursor c = dbProductos.rawQuery("select * from productos",null);

        if(c.moveToFirst()){
            do {
                list.add(c.getString(1));
                list2.add(c.getString(3));
            } while (c.moveToNext());
        }
        if(Nombre1.equals(list.get(0))){
            desc = list2.get(0);
        }else if(Nombre1.equals(list.get(1))){
            desc = list2.get(1);
        }else if(Nombre1.equals(list.get(2))){
            desc = list2.get(2);
        }else if(Nombre1.equals(list.get(3))){
            desc = list2.get(3);
        }else if(Nombre1.equals(list.get(4))){
            desc = list.get(4);
        }
        des = (TextView)findViewById(R.id.descripcion);
        des.setText(desc);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.volver:
                Intent intent = new Intent(this, PromocionActivity.class);
                intent.putExtra("Name", Nombre.toString());
                intent.putExtra("Pass", Contrasena.toString());
                intent.putExtra("Email", Mail.toString());
                startActivity(intent);
                finish();
                break;
            case R.id.agregar:

                if(ban.equals("favorito")) {
                    ban="";
                }else{
                    dataBD = new ContentValues();
                    dataBD.put("usuario", Nombre);
                    dataBD.put("producto", Nombre1);


                    dbFavoritos.insert("Favoritos", null, dataBD);
                }
                break;
            case R.id.eliminar:
                dbFavoritos.delete("Favoritos","id='"+borrar+"'",null);
                break;
        }
    }
}
