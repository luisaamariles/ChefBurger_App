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
        //bAceptar.setEnabled(false);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();

       // String name= extras.getString("Name");
        //String pass = extras.getString("Pass");

        //Toast.makeText(this, "user: "+name+" contrasena: "+pass,Toast.LENGTH_SHORT).show();
//String...los que llegan string user=extras.getstring();
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

        Toast.makeText(this,Contrasena,Toast.LENGTH_SHORT).show();


        if (vacio1.equals("") || vacio2.equals("")) {
            Toast.makeText(this,"Campos Vacios",Toast.LENGTH_SHORT).show();
            //datos.setText("Campos vacios");
        }else{a=prefs.getString("nombre","");
            b=prefs.getString("contraseña","");
            //Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,b,Toast.LENGTH_SHORT).show();
            if(vacio1.equals(a)&& vacio2.equals(b)) {
                Intent intent = new Intent(this, NavActivity.class);
               // vacio1=prefs.getString("nombre","");
                //vacio2=prefs.getString("mail","");
                intent.putExtra("Name",prefs.getString("nombre",""));
                intent.putExtra("Pass",prefs.getString("contraseña",""));
                intent.putExtra("Email",prefs.getString("mail",""));
                editor.putInt("var",1);
                editor.commit();
                startActivity(intent);
                finish();

            }else{
                Toast.makeText(this,"Registrese!",Toast.LENGTH_SHORT).show();
                //datos.setText("Registrese!");
            }

        }
    }
    public void handleOnClick(View view)
    {
        //bAceptar.setEnabled(true);
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
            // log.d("user",user); //para imprimir en la consola
            //String name = data.getExtras().getString("Name");
            //String pass = data.getExtras().getString("Pass");
            // Log.d("Nombre", name);
            // Log.d("contraseña", pass);

            //Nombre = data.getExtras().getString("Name");
            //Contrasena = data.getExtras().getString("Pass");
           // Mail=data.getExtras().getString("Email");
            //editor.putString("nombre",Nombre);
            //editor.putString("contraseña",Contrasena);
            //editor.putString("mail",Mail);
           // editor.commit();



            //Toast.makeText(this, "user: "+Nombre+" contrasena: "+Contrasena+"email"+Mail, Toast.LENGTH_SHORT).show();

        }
        if (requestCode==1234 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }
    }
}