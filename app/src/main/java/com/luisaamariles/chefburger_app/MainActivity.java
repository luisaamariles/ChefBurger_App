package com.luisaamariles.chefburger_app;

import android.os.Bundle;
import android.widget.FrameLayout;


public class MainActivity extends NavActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
    }
}
