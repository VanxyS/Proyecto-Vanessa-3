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
import android.widget.Toast;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class EliminarUsuario extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_eliminar_usuario, container, false);

        final ListView listView = view.findViewById(R.id.listUsuarios);

        apiMethods.listarUsuarios(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/listaUsuarios.php", listView);

        final EditText usuario = view.findViewById(R.id.usuarioEliminar);

        Button button = view.findViewById(R.id.modificarPermisos);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!usuario.getText().toString().isEmpty())
                {
                    String nombre = usuario.getText().toString();
                    apiMethods.eliminarUsuario(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/eliminarUsuario.php", nombre);

                    apiMethods.listarUsuarios(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/listaUsuarios.php", listView);
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "No hay ningun usuario", Toast.LENGTH_LONG).show();
                }
            }
        });

        apiMethods.listarUsuarios(getActivity().getApplicationContext(), "http://192.168.1.35/ProyectoDAM/listaUsuarios.php", listView);

        // Inflate the layout for this fragment
        return view;
    }
}
