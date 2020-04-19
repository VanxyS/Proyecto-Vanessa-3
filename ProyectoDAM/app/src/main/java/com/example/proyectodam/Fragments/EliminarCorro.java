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

public class EliminarCorro extends Fragment
{
    APIMethods apiMethods =  new APIMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar_corro, container, false);

        final EditText pueblo = view.findViewById(R.id.nombrePuebloEliminar);
        final EditText dia = view.findViewById(R.id.fecha);
        Button button = view.findViewById(R.id.button2);
        final ListView listView = view.findViewById(R.id.listCorros);

        apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                APIMethods apiMethods = new APIMethods();

                apiMethods.eliminarCorro(getActivity().getApplicationContext(),"http://192.168.1.39/ProyectoDAM/eliminarCorro.php", pueblo.getText().toString(), dia.getText().toString(), EliminarCorro.this);

                apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
