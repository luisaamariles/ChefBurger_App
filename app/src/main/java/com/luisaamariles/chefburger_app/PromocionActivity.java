package com.luisaamariles.chefburger_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */

public class PromocionActivity extends NavActivity {

    //final String[] opciones = new String[]{"Argentina+Francesas", "Italiana+Galleta", "Veggie+Rusticas","Campestre+Chips", "Americanas+Brownie"};

    private List[] datos=
            new List[]{
                    new List("Argentina+Francesas","1 semana"),
                    new List("Italiana+Galleta","2 semanas"),
                    new List("Veggie+Rusticas","1 semana"),
                    new List("Campestre+Chips","1 semana"),
                    new List("Americanas+Brownie","2 semanas"),
            };
    ListView listView;
    FrameLayout contentFrameLayout;
    String Name, fecha;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.promocion);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.promocion, contentFrameLayout);

        Bundle extras = getIntent().getExtras();
        Nombre = extras.getString("Name");
        Contrasena = extras.getString("Pass");
        Mail=extras.getString("Email");

        //ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.nom));

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

//                Toast.makeText(PromocionActivity.this, i , Toast.LENGTH_LONG).show();
                switch(i){
                    case 0:
                        Name= "Argentina+Francesas";
                        fecha = "1 semana";
                        Intent intent = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent.putExtra("Name1", Name.toString());
                        intent.putExtra("fecha", fecha.toString());
                        intent.putExtra("Name",Nombre.toString());
                        intent.putExtra("Pass",Contrasena.toString());
                        intent.putExtra("Email",Mail.toString());
                        startActivity(intent);
                        finish();
                        Toast.makeText(PromocionActivity.this, "1", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Name= "Italiana+Galleta";
                        fecha = "2 semana";
                        Intent intent2 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent2.putExtra("Name1", Name.toString());
                        intent2.putExtra("fecha", fecha.toString());
                        intent2.putExtra("Name",Nombre.toString());
                        intent2.putExtra("Pass",Contrasena.toString());
                        intent2.putExtra("Email",Mail.toString());
                        startActivity(intent2);
                        finish();
                        Toast.makeText(PromocionActivity.this, "2", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Name= "Veggie+Rusticas";
                        fecha = "1 semana";
                        Intent intent3 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent3.putExtra("Name1", Name.toString());
                        intent3.putExtra("fecha", fecha.toString());
                        intent3.putExtra("Name",Nombre.toString());
                        intent3.putExtra("Pass",Contrasena.toString());
                        intent3.putExtra("Email",Mail.toString());
                        startActivity(intent3);
                        finish();
                        Toast.makeText(PromocionActivity.this, "3", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Name= "Campestre+Chips";
                        fecha = "1 semana";
                        Intent intent4 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent4.putExtra("Name1", Name.toString());
                        intent4.putExtra("fecha", fecha.toString());
                        intent4.putExtra("Name",Nombre.toString());
                        intent4.putExtra("Pass",Contrasena.toString());
                        intent4.putExtra("Email",Mail.toString());
                        startActivity(intent4);
                        finish();
                        Toast.makeText(PromocionActivity.this, "4", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Name= "Americanas+Brownie";
                        fecha = "2 semana";
                        Intent intent5 = new Intent(PromocionActivity.this, PromfinActivity.class);
                        intent5.putExtra("Name1", Name.toString());
                        intent5.putExtra("fecha", fecha.toString());
                        intent5.putExtra("Name",Nombre.toString());
                        intent5.putExtra("Pass",Contrasena.toString());
                        intent5.putExtra("Email",Mail.toString());
                        startActivity(intent5);
                        finish();
                        Toast.makeText(PromocionActivity.this, "5", Toast.LENGTH_LONG).show();
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

