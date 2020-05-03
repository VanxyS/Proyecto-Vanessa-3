package com.example.proyectodam.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class InfoCorro extends Fragment
{
    FragmentActivity fragmentActivity;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    APIMethods apiMethods = new APIMethods();
    String puebloTexto;
    String diaTexto;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.corros_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_info_corro, container, false);
//        Toolbar toolbar = view.findViewById(R.id.toolbar2);
//        toolbar.inflateMenu(R.menu.corros_menu);
//        setHasOptionsMenu(true);

        //ListView listView = view.findViewById(R.id.listLuchadoresCorro);

        TextView pueblo = view.findViewById(R.id.textView5);
        TextView dia = view.findViewById(R.id.textView6);
        TextView hora = view.findViewById(R.id.textView7);
        TextView lugar = view.findViewById(R.id.textView8);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            puebloTexto = bundle.getString("pueblo", null);
            diaTexto = bundle.getString("dia", null);
            String horaTexto = bundle.getString("hora", null);
            String lugarTexto = bundle.getString("lugar", null);


            pueblo.setText(puebloTexto);
            dia.setText(diaTexto);
            hora.setText(horaTexto);
            lugar.setText(lugarTexto);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.eliminarCorroCorros:
                Toast.makeText(getActivity().getApplicationContext(), "Eliminar corro", Toast.LENGTH_LONG).show();
                break;

            case R.id.modificarCorro:

                break;
        }
        return true;
    }
}
