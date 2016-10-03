package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.content.Intent;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav);
        Bundle extras = getIntent().getExtras();

        Nombre = extras.getString("Name");
        Contrasena = extras.getString("Pass");
        Mail=extras.getString("Email");

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
                        intentp.putExtra("Name",Nombre.toString());
                        intentp.putExtra("Pass",Contrasena.toString());
                        intentp.putExtra("Email",Mail.toString());
                        startActivity(intentp);
                        finish();
                        break;
                    case (1):
                        Intent intent = new Intent(NavActivity.this, MenuActivity.class);
                        intent.putExtra("Name",Nombre.toString());
                        intent.putExtra("Pass",Contrasena.toString());
                        intent.putExtra("Email",Mail.toString());
                        startActivity(intent);
                        finish();
                        break;
                    case (2):
                        Intent intent2 = new Intent(NavActivity.this, MainActivity.class);
                        intent2.putExtra("Name",Nombre.toString());
                        intent2.putExtra("Pass",Contrasena.toString());
                        intent2.putExtra("Email",Mail.toString());
                        startActivity(intent2);
                        finish();
                        break;
                    case (3):
                        Intent intent3 = new Intent(NavActivity.this, PromocionActivity.class);
                        intent3.putExtra("Name",Nombre.toString());
                        intent3.putExtra("Pass",Contrasena.toString());
                        intent3.putExtra("Email",Mail.toString());
                        startActivity(intent3);
                        finish();
                        break;
                    case (4):
                        break;
                }
                if (i == 4) {
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

