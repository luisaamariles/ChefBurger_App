package com.luisaamariles.chefburger_app;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
public class PromfinActivity extends NavActivity{

    String Nombre, Fecha;
    TextView Nomb, fec;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.promfin);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.promfin, contentFrameLayout);
        Nomb = (TextView) findViewById(R.id.nombre);
        fec = (TextView) findViewById(R.id.fechap);
        Bundle extras = getIntent().getExtras();
        Nombre = extras.getString("Name");
        Nomb.setText(Nombre);
        Fecha = extras.getString("fecha");
        fec.setText(Fecha);
    }
}
