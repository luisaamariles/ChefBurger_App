package com.luisaamariles.chefburger_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;


public class MainActivity extends NavActivity{

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");
    }
}
