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

public class ModificarPuntuacion extends Fragment
{
    APIMethods apiMethods = new APIMethods();
    String puntos = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_modificar_puntuacion, container, false);

        final ListView listView = view.findViewById(R.id.listLuchadoresPuntuacion);

        apiMethods.listarLuchadoresPuntuacion("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        final EditText nombre = view.findViewById(R.id.luchadorAniadirPuntuacion);
        final EditText apellido1 = view.findViewById(R.id.apellido1Sumar);

        Button button = view.findViewById(R.id.sumarPutuacionLuchador);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final RadioGroup group = view.findViewById(R.id.radioLuchadoresPuntuacion);
                final int idRadioButtonActivo = group.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(idRadioButtonActivo);

                System.out.println(idRadioButtonActivo);

                switch (idRadioButtonActivo)
                {
                    case 2131231022:
                        puntos = "10";
                        break;

                    case 2131231023:
                        puntos = "8";
                        break;

                    case 2131231024:
                        puntos = "6";
                        break;

                    case 2131231021:
                        puntos = "4";
                        break;

                    case 2131231004:
                        puntos = "2";
                        break;

                    case -1:
                        Toast.makeText(getActivity().getApplicationContext(), "No hay ningún puesto seleccionado", Toast.LENGTH_LONG).show();
                        break;
                }

                if(!nombre.getText().toString().isEmpty() && !apellido1.getText().toString().isEmpty())
                {
                    String nombreLuchador = nombre.getText().toString();
                    String apellidoLuchador = apellido1.getText().toString();
                    System.out.println(nombre.getText().toString());
                    apiMethods.modificarPuntuacion(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/cambiarPuntuacion.php", nombreLuchador, apellidoLuchador, puntos);

                    apiMethods.listarLuchadoresPuntuacion("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "No hay ningun usuario", Toast.LENGTH_LONG).show();
                }

                apiMethods.listarLuchadoresPuntuacion("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
