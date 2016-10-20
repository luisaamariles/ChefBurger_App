package com.luisaamariles.chefburger_app;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MisFavoritosFragment extends Fragment {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String Nombre, Contrasena, Mail,Nom;
    SQLiteDatabase dbFavoritos;
    ArrayList<String> list;

    public MisFavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mis_favoritos, container, false);
        FavoritosSQLiteHelper favoritos = new FavoritosSQLiteHelper(getActivity(), "FavoritosBD", null, 1);
        dbFavoritos = favoritos.getWritableDatabase();
        prefs = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();
        Nombre = prefs.getString("nombre", "");
        Contrasena = prefs.getString("contraseña", "");
        Mail = prefs.getString("mail", "");
        list = new ArrayList<String>();
        Cursor c = dbFavoritos.rawQuery("select * from favoritos where usuario='" + Nombre + "'", null);

        if (c.moveToFirst()) {
            do {
                list.add(c.getString(2));
            } while (c.moveToNext());
        }
        // A. Creamos el arreglo de Strings para llenar la lista

        // B. Creamos un nuevo ArrayAdapter con nuestra lista de cosasPorHacer
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, list);

        // C. Seleccionamos la lista de nuestro layout
        ListView miLista = (ListView) rootView.findViewById(R.id.milista);

        // D. Asignamos el adaptador a nuestra lista
        miLista.setAdapter(arrayAdapter);
        //registrarEventos();
        ListView lista1 = (ListView) rootView.findViewById(R.id.milista);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        Nom=list.get(0);
                        Intent intent = new Intent(getActivity(), EliminarActivity.class);
                        intent.putExtra("Name1", Nom.toString());
                        intent.putExtra("Name2", Nombre.toString());
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        Nom=list.get(1);
                        Intent intent2 = new Intent(getActivity(), EliminarActivity.class);
                        intent2.putExtra("Name1", Nom.toString());
                        getActivity().startActivity(intent2);
                        break;
                    case 2:
                        Nom=list.get(2);
                        Intent intent3 = new Intent(getActivity(), EliminarActivity.class);
                        intent3.putExtra("Name1", Nom.toString());
                        getActivity().startActivity(intent3);
                        break;
                    case 3:
                        Nom=list.get(3);
                        Intent intent4 = new Intent(getActivity(), EliminarActivity.class);
                        intent4.putExtra("Name1", Nom.toString());
                        getActivity().startActivity(intent4);
                        break;
                    case 4:
                        Nom=list.get(4);
                        Intent intent5 = new Intent(getActivity(), EliminarActivity.class);
                        intent5.putExtra("Name1", Nom.toString());
                        getActivity().startActivity(intent5);
                        break;
                }
            }
        });

        return rootView;
    }

}