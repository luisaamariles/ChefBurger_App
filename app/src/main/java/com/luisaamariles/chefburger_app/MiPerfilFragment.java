package com.luisaamariles.chefburger_app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiPerfilFragment extends Fragment {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String Nombre,Contrasena, Mail;
    public MiPerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mi_perfil, container, false);//Inflate Layout
        TextView text = (TextView) view.findViewById(R.id.Nom);
        TextView text2 = (TextView) view.findViewById(R.id.Correo);//Find textview Id
        prefs =getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();
        Nombre=prefs.getString("nombre","");
        Contrasena = prefs.getString("contraseña","");
        Mail= prefs.getString("mail","");
        text.setText("Nombre:"+Nombre);//set string over textview
        text2.setText("Email:"+Mail);
        return view;
    }
    //http://mundogeek.net/android/fragmentos/comunicacion.htm#Enviar

}
