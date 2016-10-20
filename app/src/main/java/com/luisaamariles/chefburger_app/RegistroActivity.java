package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luisa Maria Amariles on 05/09/2016.
 */
public class RegistroActivity  extends AppCompatActivity implements View.OnClickListener {
    EditText eName,ePass,eRPass,Email;
    TextView datos;
    String Pass,RPass,Nombre, Contrasena,Mail;
    Button bAceptar;
    ContentValues dataBD;
    SQLiteDatabase dbUsuarios;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.registro);

        UsuariosSQLiteHelper usuarios = new UsuariosSQLiteHelper(this,"UsuariosBD",null,1);
        dbUsuarios = usuarios.getWritableDatabase();

        eName = (EditText) findViewById(R.id.eName);
        ePass = (EditText) findViewById(R.id.ePass);
        eRPass = (EditText) findViewById(R.id.eRPass);
        Email = (EditText) findViewById(R.id.eEmail);
        datos = (TextView) findViewById(R.id.datos);
        Button Cancelar = (Button) findViewById(R.id.bCancelar);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        bAceptar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);
        getSupportActionBar().hide();
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bAceptar:
                //DO something
                String vacio1= eName.getText().toString();
                String vacio2= ePass.getText().toString();
                String vacio3= eRPass.getText().toString();
                String vacio4= Email.getText().toString();

                Cursor c = dbUsuarios.rawQuery("select * from usuarios where usuario='"+vacio1+"'",null);

                if(c.moveToFirst()){
                    Nombre = c.getString(1);
                    Contrasena = c.getString(2);
                    Mail = c.getString(3);
                }else{
                    Nombre = "NULL";
                    Contrasena = "NULL";
                    Mail = "NULL";
                }

                if (vacio1.equals("") || vacio2.equals("") || vacio3.equals("") || vacio4.equals("")) {
                    Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show();
                }else {
                    if (Nombre.equals(vacio1)) {
                        Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        eName.setText("");
                    } else {
                        Pass = ePass.getText().toString();
                        RPass = eRPass.getText().toString();

                        if (Pass.toString().equals(RPass)) {

                            dataBD = new ContentValues();
                            dataBD.put("usuario", vacio1);
                            dataBD.put("contraseña", vacio2);
                            dataBD.put("correo", vacio4);

                            dbUsuarios.insert("Usuarios", null, dataBD);

                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Las contraseñas no coinciden!", Toast.LENGTH_SHORT).show();
                            ePass.setText("");
                            eRPass.setText("");
                        }
                    }
                }
                break;
            case R.id.bCancelar:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
        }

    }

}