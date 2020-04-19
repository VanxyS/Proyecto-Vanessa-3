package com.example.proyectodam.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class MediosFragmentPuntuacion extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medios, container, false);

        ListView listView = view.findViewById(R.id.mediosLuchadores);

        APIMethods apiMethods = new APIMethods();

        apiMethods.listarMediosPuntuacion("http://192.168.1.39/ProyectoDAM/listarPuntuacion.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
