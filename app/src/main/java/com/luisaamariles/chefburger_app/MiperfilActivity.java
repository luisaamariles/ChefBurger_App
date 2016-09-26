package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luisa Maria Amariles on 04/09/2016.
 */
public class MiperfilActivity extends AppCompatActivity {
    String Nombre,Contrasena, Mail;
    TextView Nomb,cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miperfil);
        getSupportActionBar().show();
        Nomb = (TextView) findViewById(R.id.Nom);
        cont = (TextView) findViewById(R.id.Correo);
        Bundle extras = getIntent().getExtras();
        Nombre = extras.getString("Name");
        Contrasena = extras.getString("Pass");
        Mail=extras.getString("Email");
        //Toast.makeText(this, "user: "+Nombre+" contrasena: "+Contrasena+"email"+Mail, Toast.LENGTH_SHORT).show();
        Nomb.setText("Nombre: "+Nombre);
        cont.setText("Email: "+Mail);
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.principal:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Name",Nombre.toString());
                intent.putExtra("Pass",Contrasena.toString());
                intent.putExtra("Email",Mail.toString());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

