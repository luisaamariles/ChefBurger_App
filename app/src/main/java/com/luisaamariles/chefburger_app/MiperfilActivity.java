package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Luisa Maria Amariles on 04/09/2016.
 */
public class MiperfilActivity extends NavActivity  {
    String Nombre,Contrasena, Mail;
    private ViewPager mViewPager;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.miperfil, contentFrameLayout);

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        mViewPager.setAdapter(pagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Mi perfil").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Mis favoritos").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });

        //enviar();
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MiPerfilFragment();
                case 1: return new MisFavoritosFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}




