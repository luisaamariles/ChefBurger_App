package com.luisaamariles.chefburger_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiPerfilFragment extends Fragment {


    public MiPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String data =getArguments().getString("name");
        Toast.makeText(getActivity(),data, Toast.LENGTH_LONG).show();
        View v=inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        return v;
    }

}
