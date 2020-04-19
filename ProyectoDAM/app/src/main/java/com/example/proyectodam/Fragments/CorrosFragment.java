package com.example.proyectodam.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class CorrosFragment extends Fragment
{
    APIMethods apiMethods = new APIMethods();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_corros, container, false);

        ListView listView = view.findViewById(R.id.listCorrosMuestra);

        apiMethods.listarCorros("http://192.168.1.39/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        return view;
    }
}
