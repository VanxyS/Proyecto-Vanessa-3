package com.example.proyectodam.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.Corro;
import com.example.proyectodam.Drawer;
import com.example.proyectodam.Items.ItemCorro;
import com.example.proyectodam.R;

public class CorrosFragment extends Fragment
{
    APIMethods apiMethods = new APIMethods();
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentActivity corros;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_corros, container, false);

        ListView listView = view.findViewById(R.id.listCorrosMuestra);

        apiMethods.listarCorros("http://192.168.1.35/ProyectoDAM/listarCorros.php", listView, getActivity().getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ItemCorro itemCorro = (ItemCorro) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("pueblo", itemCorro.getPueblo());
                bundle.putString("dia", itemCorro.getDia());
                bundle.putString("hora", itemCorro.getHora());
                bundle.putString("lugar", itemCorro.getLugar());

                Fragment infoCorro = new InfoCorro();
                infoCorro.setArguments(bundle);

                fragmentManager = corros.getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, infoCorro);
                fragmentTransaction.commit();

                //Intent intent = new Intent(getContext(), EliminarCorro.class);
                //startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        corros = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
