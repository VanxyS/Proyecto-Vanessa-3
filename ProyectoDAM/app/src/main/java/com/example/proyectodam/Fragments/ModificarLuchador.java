package com.example.proyectodam.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class ModificarLuchador extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_modificar_luchador, container, false);

        final ListView listView = view.findViewById(R.id.listLuchadoresActuales);

        apiMethods.listarLuchadores("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        final EditText nombre = view.findViewById(R.id.nombreLuchadorModificar);
        final EditText apellido = view.findViewById(R.id.apellidoLuchadorModificar);
        final EditText nombre1 = view.findViewById(R.id.nuevoNombreLuchador);
        final EditText apellido1 = view.findViewById(R.id.nuevoApellido1Modificar);
        final EditText apellido2 = view.findViewById(R.id.nuevoApellido2Modificar);
        final EditText edad = view.findViewById(R.id.nuevaEdadModificar);
        final EditText peso = view.findViewById(R.id.nuevoPesoMod);
        final EditText categoria = view.findViewById(R.id.nuevaCategoriaMod);

        Button button = view.findViewById(R.id.botonLuchadorModificar);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nombreL = nombre.getText().toString();
                String apellidoL = apellido.getText().toString();
                String nombreN = nombre1.getText().toString();
                String apellidoN = apellido1.getText().toString();
                String apellido2N = apellido2.getText().toString();
                String edaN = edad.getText().toString();
                String pesoN = peso.getText().toString();
                String categoriaN = categoria.getText().toString();

                apiMethods.modificarLuchador(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/modificarLuchador.php",
                        nombreL, apellidoL, nombreN, apellidoN, apellido2N, edaN, pesoN, categoriaN);
                apiMethods.listarLuchadores("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());
            }
        });

        apiMethods.listarLuchadores("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
