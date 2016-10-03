package com.luisaamariles.chefburger_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
public class PromfinActivity extends AppCompatActivity implements View.OnClickListener{
    String Nombre,Contrasena, Mail;
    String Nombre1, Fecha, desc;
    TextView Nomb, fec, des;
    Button bvolver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promfin);
        Nomb = (TextView) findViewById(R.id.nombre);
        fec = (TextView) findViewById(R.id.fechap);
        bvolver = (Button) findViewById(R.id.volver);
        bvolver.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        Nombre1 = extras.getString("Name1");
        Nomb.setText(Nombre1);
        Fecha = extras.getString("fecha");
        fec.setText(Fecha);
        Nombre = extras.getString("Name");
        Contrasena = extras.getString("Pass");
        Mail=extras.getString("Email");

        mostrar();



    }

    private void mostrar(){

        if(Nombre1.equals("Argentina+Francesas")){
            desc = "Hamburguesa argentina + papas francesas por $21.900";
        }else if(Nombre1.equals("Italiana+Galleta")){
            desc = "Hamburguesa Italiana + galleta al horno con helado por $22.800";
        }else if(Nombre1.equals("Veggie+Rusticas")){
            desc = "Hamburguesa vegetariana + papas rusticas por $21.100";
        }else if(Nombre1.equals("Campestre+Chips")){
            desc = "Ensalada campestre + papas chips por $11.800";
        }else if(Nombre1.equals("Americanas+Brownie")){
            desc = "Papas americanas + brownie al horno por $13.800";
        }
        des = (TextView)findViewById(R.id.descripcion);
        des.setText(desc);

    }

    public void onClick(View v){
        Intent intent = new Intent(this, PromocionActivity.class);
        intent.putExtra("Name",Nombre.toString());
        intent.putExtra("Pass",Contrasena.toString());
        intent.putExtra("Email",Mail.toString());
        startActivity(intent);
        finish();
    }
}
