package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luisa Maria Amariles on 03/09/2016.
 */
public class LogginActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText Name,Pass;
    Button bAceptar;
    TextView datos;
    String Nombre,Contrasena, Mail;
    ContentValues dataBD;
    SQLiteDatabase dbUsuarios;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        UsuariosSQLiteHelper usuarios = new UsuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbUsuarios = usuarios.getWritableDatabase();

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Name = (EditText) findViewById(R.id.eName);
        Pass= (EditText) findViewById(R.id.ePass);
        datos = (TextView) findViewById(R.id.datos);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        bAceptar.setOnClickListener(this);
        getSupportActionBar().hide();

        if(prefs.getInt("var",-1)==1){
            Intent intent = new Intent(this, NavActivity.class);
            intent.putExtra("Name",prefs.getString("nombre",""));
            intent.putExtra("Pass",prefs.getString("contraseña",""));
            intent.putExtra("Email",prefs.getString("mail",""));

            startActivity(intent);
            finish();
        }

    }

    public void onClick(View v){
        //Iniciando la actividad Visor

        String vacio1= Name.getText().toString();
        String a,b;
        String vacio2= Pass.getText().toString();
        Cursor c = dbUsuarios.rawQuery("select * from usuarios where usuario='"+vacio1+"'",null);

        if(c.moveToFirst()){
            Nombre = c.getString(1);
            Contrasena = c.getString(2);
            Mail = c.getString(3);
        }

        editor.putString("nombre",Nombre);
        editor.putString("contraseña",Contrasena);
        editor.putString("mail",Mail);
        editor.commit();


        if (vacio1.equals("") || vacio2.equals("")) {
            Toast.makeText(this,"Campos Vacios",Toast.LENGTH_SHORT).show();
        }else{a=prefs.getString("nombre","");
            b=prefs.getString("contraseña","");
            if(vacio1.equals(a)&& vacio2.equals(b)) {
                Intent intent = new Intent(this, NavActivity.class);
                intent.putExtra("Name",prefs.getString("nombre",""));
                intent.putExtra("Pass",prefs.getString("contraseña",""));
                intent.putExtra("Email",prefs.getString("mail",""));
                editor.putInt("var",1);
                editor.commit();
                startActivity(intent);
                finish();

            }else{
                Toast.makeText(this,"Registrese!",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void handleOnClick(View view)
    {
        switch(view.getId())
        {
            case R.id.registro:

                Intent intent2 = new Intent(this, RegistroActivity.class);
                startActivityForResult(intent2, 1234);
                Name.setText("");
                Pass.setText("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {

        }
        if (requestCode==1234 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }
    }
}