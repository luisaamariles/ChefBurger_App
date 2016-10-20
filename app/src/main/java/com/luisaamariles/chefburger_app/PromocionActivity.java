package com.luisaamariles.chefburger_app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */

public class PromocionActivity extends NavActivity {

    private List[] datos;
    ListView listView;
    String Name, fecha;
    SQLiteDatabase dbProductos;
    ContentValues dataBD;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ArrayList<String> list,list2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.promocion, contentFrameLayout);

        ProductosSQLiteHelper productos= new ProductosSQLiteHelper(this,"ProductosBD",null,1);
        dbProductos = productos.getWritableDatabase();

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");

        Cursor c1 = dbProductos.rawQuery("select * from productos",null);
        if(c1.moveToFirst()){

        }else{
            dataBD = new ContentValues();
            dataBD.put("producto", "Argentina+Francesas");
            dataBD.put("tiempo", "1 semana");
            dataBD.put("descripcion", "Hamburguesa argentina + papas francesas por $21.900");
            dbProductos.insert("Productos", null, dataBD);
            dataBD = new ContentValues();
            dataBD.put("producto", "Italiana+Galleta");
            dataBD.put("tiempo", "2 semana");
            dataBD.put("descripcion", "Hamburguesa Italiana + galleta al horno con helado por $22.800");
            dbProductos.insert("Productos", null, dataBD);
            dataBD = new ContentValues();
            dataBD.put("producto", "Veggie+Rusticas");
            dataBD.put("tiempo", "1 semana");
            dataBD.put("descripcion", "Hamburguesa vegetariana + papas rusticas por $21.100");
            dbProductos.insert("Productos", null, dataBD);
            dataBD = new ContentValues();
            dataBD.put("producto", "Campestre+Chips");
            dataBD.put("tiempo", "1 semana");
            dataBD.put("descripcion", "Ensalada campestre + papas chips por $11.800");
            dbProductos.insert("Productos", null, dataBD);
            dataBD = new ContentValues();
            dataBD.put("producto", "Americanas+Brownie");
            dataBD.put("tiempo", "2 semana");
            dataBD.put("descripcion", "Papas americanas + brownie al horno por $13.800");
            dbProductos.insert("Productos", null, dataBD);
        }
        list = new ArrayList<String>();
        list2=new ArrayList<String>();

        Cursor c = dbProductos.rawQuery("select * from productos",null);

        if(c.moveToFirst()){
            do {
                    list.add(c.getString(1));
                    list2.add(c.getString(2));
            } while (c.moveToNext());
        }

        datos= new List[]{
                new List(list.get(0), list2.get(0)),
                new List(list.get(1), list2.get(1)),
                new List(list.get(2), list2.get(2)),
                new List(list.get(3), list2.get(3)),
                new List(list.get(4), list2.get(4)),
        };

        Adapter adaptador = new Adapter(this);
        listView = (ListView) findViewById(R.id.menu);

        listView.setAdapter(adaptador);

        registrarEventos();
    }

   private void registrarEventos(){

        /// selecciona la lista en pantalla segun su ID
        ListView lista1 = (ListView) findViewById(R.id.menu);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 0:
                        Name= list.get(0);
                        fecha = list2.get(0);
                        Intent intent = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent.putExtra("Name1", Name.toString());
                        intent.putExtra("fecha", fecha.toString());
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Name= list.get(1);
                        fecha = list2.get(1);
                        Intent intent2 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent2.putExtra("Name1", Name.toString());
                        intent2.putExtra("fecha", fecha.toString());
                        startActivity(intent2);
                        finish();
                        break;
                    case 2:
                        Name= list.get(2);
                        fecha = list.get(2);
                        Intent intent3 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent3.putExtra("Name1", Name.toString());
                        intent3.putExtra("fecha", fecha.toString());
                        startActivity(intent3);
                        finish();
                        break;
                    case 3:
                        Name= list.get(3);
                        fecha = list2.get(3);
                        Intent intent4 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent4.putExtra("Name1", Name.toString());
                        intent4.putExtra("fecha", fecha.toString());
                        startActivity(intent4);
                        finish();
                        break;
                    case 4:
                        Name= list.get(4);
                        fecha = list2.get(4);
                        Intent intent5 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent5.putExtra("Name1", Name.toString());
                        intent5.putExtra("fecha", fecha.toString());
                        startActivity(intent5);
                        finish();
                        break;
                }
            }
        });

    }


    class Adapter extends ArrayAdapter<List> {
        Activity context;

        public Adapter(Activity context){
            super(context, R.layout.list , datos);
            this.context = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list,null);

            TextView nombre = (TextView)item.findViewById(R.id.nom1);
            nombre.setText(datos[position].getNombre());

            TextView fecha = (TextView)item.findViewById(R.id.fec1);
            fecha.setText(datos[position].getFecha());

            return (item);
        }
    }
}

