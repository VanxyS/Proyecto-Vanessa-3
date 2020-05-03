package com.example.proyectodam.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class PesadosFragmentPuntuacion extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesados, container, false);

        ListView listView = view.findViewById(R.id.pesadosLuchadores);

        APIMethods apiMethods = new APIMethods();

        apiMethods.listarPesadosPuntuacion("http://192.168.1.35/ProyectoDAM/listarPuntuacion.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
