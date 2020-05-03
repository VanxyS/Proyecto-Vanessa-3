package com.example.proyectodam.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class EliminarLuchador extends Fragment
{
    APIMethods apiMethods =  new APIMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar_luchador, container, false);

        final EditText nombre = view.findViewById(R.id.nombreLuchadorEliminar);
        final EditText apellido1 = view.findViewById(R.id.apellidoLuchadorEliminar);
        final ListView listView = view.findViewById(R.id.listLuchadoresEliminar);
        Button button = view.findViewById(R.id.buttonEliminarLuchador);

        apiMethods.listarLuchadores("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                APIMethods apiMethods = new APIMethods();

                apiMethods.eliminarLuchador(getActivity().getApplicationContext(),"http://192.168.1.35/ProyectoDAM/eliminarLuchador.php", nombre.getText().toString(), apellido1.getText().toString());

                apiMethods.listarLuchadores("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
