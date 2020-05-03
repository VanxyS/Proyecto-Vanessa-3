package com.example.proyectodam.Fragments;

import android.app.Activity;
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

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.R;

public class InfoLuchador extends Fragment
{
    FragmentActivity fragmentActivity;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    APIMethods apiMethods = new APIMethods();
    String nombreTexto;
    String apellido1Texto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.luchadores_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item)
//    {
//        switch (item.getItemId())
//        {
//            case R.id.eliminarLuchadorLuchadores:
//                apiMethods.eliminarCorro(getActivity().getApplicationContext(),"http://192.168.1.42/ProyectoDAM/eliminarCorro.php", puebloTexto, diaTexto);
//
//                System.out.println("entro");
//                fragmentManager = fragmentActivity.getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container_fragment, new LuchadoresFragment());
//                fragmentTransaction.commit();
//                break;
//
//            case R.id.modificarCorro:
//
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_info_luchador, container, false);
//        Toolbar toolbar = view.findViewById(R.id.toolbar3);
//        toolbar.inflateMenu(R.menu.luchadores_menu);
//        setHasOptionsMenu(true);

        //ListView listView = view.findViewById(R.id.listLuchadoresCorro);

        TextView nombre = view.findViewById(R.id.textView9);
        TextView apellido1 = view.findViewById(R.id.textView10);
        TextView apellido2 = view.findViewById(R.id.textView11);
        TextView edad = view.findViewById(R.id.textView12);
        TextView peso = view.findViewById(R.id.textView13);
        TextView categoria = view.findViewById(R.id.textView14);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            nombreTexto = bundle.getString("nombre", null);
            apellido1Texto = bundle.getString("apellido1", null);
            String apellido2Texto = bundle.getString("apellido2", null);
            String edadTexto = bundle.getString("edad", null);
            String pesoTexto = bundle.getString("peso", null);
            String categoriaTexto = bundle.getString("categoria", null);


            nombre.setText(nombreTexto);
            apellido1.setText(apellido1Texto);
            apellido2.setText(apellido2Texto);
            edad.setText(edadTexto);
            peso.setText(pesoTexto);
            categoria.setText(categoriaTexto);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
