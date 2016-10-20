package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Luisa Maria Amariles on 24/09/2016.
 */
public class NavActivity extends AppCompatActivity {
    private String[] opciones = new String[]{"Mi perfil", "Menú", "Principal","Promociones", "Cerrar sesión"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    String Nombre,Contrasena, Mail;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav);

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        listView = (ListView) findViewById(R.id.menuIzq);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                switch (i) {
                    case (0):
                        Intent intentp = new Intent(NavActivity.this, MiperfilActivity.class);
                        startActivity(intentp);
                        finish();
                        break;
                    case (1):
                        Intent intent = new Intent(NavActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case (2):
                        Intent intent2 = new Intent(NavActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case (3):
                        Intent intent3 = new Intent(NavActivity.this, PromocionActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case (4):editor.putInt("var",-1);
                        editor.commit();
                        Intent intent4 = new Intent(NavActivity.this, LogginActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                if (i == 5) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();

                }
                listView.setItemChecked(i, true);
                drawerLayout.closeDrawer(listView);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

