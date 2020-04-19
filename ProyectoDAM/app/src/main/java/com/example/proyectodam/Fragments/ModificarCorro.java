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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class ModificarCorro extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_modificar_corro, container, false);

        final ListView listView = view.findViewById(R.id.listCorrosActuales);

        apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        final EditText pueblo = view.findViewById(R.id.nombrePuebloModificar);
        final EditText puebloNuevo = view.findViewById(R.id.nuevoNombrePueblo);
        final EditText dia = view.findViewById(R.id.diaCorroModificar);
        final EditText hora = view.findViewById(R.id.horaCorroModificar);
        final EditText lugar = view.findViewById(R.id.lugarCorroModificar);

        Button button = view.findViewById(R.id.botonCorroModificar);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    String nombre = pueblo.getText().toString();
                    String nombreNuevo = puebloNuevo.getText().toString();
                    String diaM = dia.getText().toString();
                    String horaM = hora.getText().toString();
                    String lugarM = lugar.getText().toString();

                    apiMethods.modificarCorro(getActivity().getApplicationContext(), "http://192.168.1.39/ProyectoDAM/modificarCorro.php", nombre, nombreNuevo, diaM, horaM, lugarM);
                    apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());
            }
        });

        apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
