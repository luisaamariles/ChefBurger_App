package com.luisaamariles.chefburger_app;

/**
 * Created by Luisa Maria Amariles on 25/09/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Luisa Maria Amariles on 18/09/2016.
 */
public class MenuActivity extends NavActivity {
    String Nombre,Contrasena, Mail;
    private ViewPager mViewPager;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.menu_activity, contentFrameLayout);

        prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
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

        ActionBar.Tab tab = actionBar.newTab().setText("Hamburguesas").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Papas").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Postres").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Ensaladas").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });

    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new HamburguesaFragment();
                case 1: return new PapasFragment();
                case 2: return new PostresFragment();
                case 3: return new EnsaladasFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}

