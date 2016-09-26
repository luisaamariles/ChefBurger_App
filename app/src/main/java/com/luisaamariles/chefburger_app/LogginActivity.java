package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */

import android.content.Intent;
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
    EditText Name,Pass;
    Button bAceptar;
    TextView datos;
    String Nombre,Contrasena, Mail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        Name = (EditText) findViewById(R.id.eName);
        Pass= (EditText) findViewById(R.id.ePass);
        datos = (TextView) findViewById(R.id.datos);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        bAceptar.setOnClickListener(this);
        //bAceptar.setEnabled(false);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();

        //String name= extras.getString("Name");
        //String pass = extras.getString("Pass");

        //Toast.makeText(this, "user: "+name+" contrasena: "+pass,Toast.LENGTH_SHORT).show();
//String...los que llegan string user=extras.getstring();
    }

    public void onClick(View v){
        //Iniciando la actividad Visor

        String vacio1= Name.getText().toString();
        String vacio2= Pass.getText().toString();
        if (vacio1.equals("") || vacio2.equals("")) {
            Toast.makeText(this,"Campos Vacios",Toast.LENGTH_SHORT).show();
            //datos.setText("Campos vacios");
        }else{
            if(vacio1.equals(Nombre)&& vacio2.equals(Contrasena)) {
                Intent intent = new Intent(this, MainActivity.class);
                /*intent.putExtra("Name",Name.getText().toString());
                intent.putExtra("Pass",Pass.getText().toString());
                intent.putExtra("Email",Mail.toString());*/
                startActivity(intent);

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

            Nombre = data.getExtras().getString("Name");
            Contrasena = data.getExtras().getString("Pass");
            Mail=data.getExtras().getString("Email");
            //Toast.makeText(this, "user: "+Nombre+" contrasena: "+Contrasena+"email"+Mail, Toast.LENGTH_SHORT).show();

        }
        if (requestCode==1234 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }
    }
}