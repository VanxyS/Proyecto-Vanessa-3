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

public class AniadirCorro extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_aniadir_corro, container, false);

        final EditText pueblo = view.findViewById(R.id.nombrePuebloAniadir);
        final EditText dia = view.findViewById(R.id.diaCorroAniadir);
        final EditText hora = view.findViewById(R.id.horaCorroAniadir);
        final EditText lugar = view.findViewById(R.id.lugarCorroAniadir);
        Button button = view.findViewById(R.id.botonCorroAniadir);

        final ListView listView = view.findViewById(R.id.listCorros);

        apiMethods.listarCorros("http://192.168.1.35/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String puebloNuevo = pueblo.getText().toString();
                String diaNuevo = dia.getText().toString();
                String horaNueva = hora.getText().toString();
                String lugarNuevo = lugar.getText().toString();

                if(!pueblo.getText().toString().isEmpty() && !dia.getText().toString().isEmpty() && !hora.getText().toString().isEmpty() &&!lugar.getText().toString().isEmpty())
                {
                    apiMethods.registrarCorro(getActivity().getApplicationContext(),"http://192.168.1.35/ProyectoDAM/registrarCorro.php", puebloNuevo, diaNuevo, horaNueva, lugarNuevo);

                    apiMethods.listarCorros("http://192.168.1.35/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());
                }
                else Toast.makeText(getActivity().getApplicationContext(), "No se admiten campos vacions", Toast.LENGTH_LONG).show();
            }
        });

        apiMethods.listarCorros("http://192.168.1.35/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
