package com.example.proyectodam.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class AniadirLuchador extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_aniadir_luchador, container, false);

        final EditText nombre = view.findViewById(R.id.nombreLucahdorAniadir);
        final EditText apellido1 = view.findViewById(R.id.apellido1Aniadir);
        final EditText apellido2 = view.findViewById(R.id.apellido2Aniadir);
        final EditText edad = view.findViewById(R.id.edadAniadir);
        final EditText peso = view.findViewById(R.id.pesoAniadir);
        final EditText categoria = view.findViewById(R.id.categoriaAniadir);
        Button button = view.findViewById(R.id.buttonAniadirLuchador);

        final ListView listView = view.findViewById(R.id.listLuchadores);

        apiMethods.listarLuchadores("http://192.168.1.39/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!nombre.getText().toString().isEmpty() && !apellido1.getText().toString().isEmpty() && !apellido2.getText().toString().isEmpty()
                        &&!edad.getText().toString().isEmpty() && !peso.getText().toString().isEmpty() && !categoria.getText().toString().isEmpty())
                {
                    apiMethods.registrarLuchador(getActivity().getApplicationContext(),"http://192.168.1.39/ProyectoDAM/registrarLuchador.php", nombre.getText().toString(),
                            apellido1.getText().toString(), apellido2.getText().toString(), edad.getText().toString(), peso.getText().toString(), categoria.getText().toString());

                    apiMethods.listarLuchadores("http://192.168.1.39/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());
                }
                else Toast.makeText(getActivity().getApplicationContext(), "No se admiten campos vacios", Toast.LENGTH_LONG).show();
            }
        });

        apiMethods.listarLuchadores("http://192.168.1.39/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
