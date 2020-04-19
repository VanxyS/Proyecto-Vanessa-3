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

public class ModificarPermisos extends Fragment
{
    APIMethods apiMethods = new APIMethods();
    String idRol = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_modificar_permisos, container, false);

        ListView listView = view.findViewById(R.id.listUsuarios);

        apiMethods.listarUsuarios(getActivity().getApplicationContext(), "http://192.168.1.39/ProyectoDAM/listaUsuarios.php", listView);

        final EditText usuario = view.findViewById(R.id.usuarioEliminar);

        Button button = view.findViewById(R.id.modificarPermisos);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final RadioGroup group = view.findViewById(R.id.radioRoles);
                final int idRadioButtonActivo = group.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(idRadioButtonActivo);

                System.out.println(idRadioButtonActivo);

                switch (idRadioButtonActivo)
                {
                    case 2131230830:
                        idRol = "1";
                        break;

                    case 2131230828:
                        idRol = "2";
                        break;

                    case 2131230829:
                        idRol = "3";
                        break;

                    case -1:
                        Toast.makeText(getActivity().getApplicationContext(), "No hay ningún rol seleccionado", Toast.LENGTH_LONG).show();
                        break;
                }

                if(!usuario.getText().toString().isEmpty())
                {
                    String nombre = usuario.getText().toString();
                    System.out.println(usuario.getText().toString());
                    apiMethods.modificarPermisos(getActivity().getApplicationContext(), "http://192.168.1.39/ProyectoDAM/modificarPermisos.php", nombre, idRol);
                }
                else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "No hay ningun usuario", Toast.LENGTH_LONG).show();
                    }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
