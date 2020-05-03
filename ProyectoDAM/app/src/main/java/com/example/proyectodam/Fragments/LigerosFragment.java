package com.example.proyectodam.Fragments;

import android.app.Activity;
import android.os.Bundle;

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
import com.example.proyectodam.Items.Item;
import com.example.proyectodam.R;

public class LigerosFragment extends Fragment
{
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentActivity corros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ligeros, container, false);

        ListView listView = view.findViewById(R.id.ligerosLuchadores);

        APIMethods apiMethods = new APIMethods();

        apiMethods.listarLuchadoresLigeros("http://192.168.1.35/ProyectoDAM/listaLuchadores.php", listView, getActivity().getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Item item = (Item) parent.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("nombre", item.getTitulo());
                bundle.putString("apellido1", item.getSubtitulo());
                bundle.putString("apellido2", item.getApellido2());
                bundle.putString("edad", Integer.toString(item.getEdad()));
                bundle.putString("peso", item.getPeso());
                bundle.putString("categoria", item.getCategoria());

                Fragment infoLuchador = new InfoLuchador();
                infoLuchador.setArguments(bundle);

                fragmentManager = corros.getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, infoLuchador);
                fragmentTransaction.commit();
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
