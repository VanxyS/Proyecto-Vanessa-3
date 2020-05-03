package com.example.proyectodam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectodam.Fragments.AniadirCorro;
import com.example.proyectodam.Fragments.AniadirLuchador;
import com.example.proyectodam.Fragments.CorrosFragment;
import com.example.proyectodam.Fragments.EliminarCorro;
import com.example.proyectodam.Fragments.EliminarLuchador;
import com.example.proyectodam.Fragments.EliminarUsuario;
import com.example.proyectodam.Fragments.LuchadoresFragment;
import com.example.proyectodam.Fragments.MainFragment;
import com.example.proyectodam.Fragments.ModificarCorro;
import com.example.proyectodam.Fragments.ModificarLuchador;
import com.example.proyectodam.Fragments.ModificarPermisos;
import com.example.proyectodam.Fragments.ModificarPuntuacion;
import com.example.proyectodam.Fragments.PuntuacionFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;

public class Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TabItem ligeros, medios, pesados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ligeros = findViewById(R.id.ligerosLuchadores);
        medios = findViewById(R.id.mediosLuchadores);
        pesados = findViewById(R.id.pesadosLuchadores);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //Seleccionamos el header
        View headerView = navigationView.getHeaderView(0);

        //Seleccionamos sus textView
        TextView textView = headerView.findViewById(R.id.textUsuario);
        TextView textView1 = headerView.findViewById(R.id.textEmail);

        //Establecemos su texto
        textView.setText(getIntent().getExtras().getString("nombre"));
        textView1.setText(getIntent().getExtras().getString("email"));

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //Por defecto
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new MainFragment());
        fragmentTransaction.commit();

        Menu menu = navigationView.getMenu();

        MenuItem menuItem = menu.getItem(1);

        MenuItem luchador = menu.getItem(2);

        String rol = getIntent().getExtras().getString("rol");

        System.out.println(rol);

        if(rol.equals("administrador"))
        {
            menuItem.setVisible(true);
        }
        else if(rol.equals("luchador"))
        {
            luchador.setVisible(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        //Para que se oculte el menú al seleccionar el fragment
        drawerLayout.closeDrawer(GravityCompat.START);

        int position = 0;

        switch (item.getItemId())
        {
            case R.id.inicio:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                fragmentTransaction.commit();
                break;

            case R.id.luchadores:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new LuchadoresFragment());
                fragmentTransaction.commit();
                break;

            case R.id.corrosBasico:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new CorrosFragment());
                fragmentTransaction.commit();
                break;

            case R.id.clasificacion:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new PuntuacionFragment(drawerLayout, fragmentManager, actionBarDrawerToggle, navigationView, toolbar));
                fragmentTransaction.commit();
                break;

            case R.id.salir:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.administrador:
                position = 1;
                break;

            case R.id.luchador:
                position = 2;
                break;

            case R.id.modificarPermisos:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ModificarPermisos());
                fragmentTransaction.commit();
                break;

            case R.id.eliminarUsuario:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new EliminarUsuario());
                fragmentTransaction.commit();
                break;

            case R.id.nuevoCorro:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new AniadirCorro());
                fragmentTransaction.commit();
                break;

            case R.id.eliminarCorro:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new EliminarCorro());
                fragmentTransaction.commit();
                break;

            case R.id.aniadirLuchador:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new AniadirLuchador());
                fragmentTransaction.commit();
                break;

            case R.id.cambiarPuntuacion:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ModificarPuntuacion());
                fragmentTransaction.commit();
                break;

            case R.id.eliminarLuchador:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new EliminarLuchador());
                fragmentTransaction.commit();
                break;

            case R.id.modificarCorro:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ModificarCorro());
                fragmentTransaction.commit();
                break;

            case R.id.modificarLuchador:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ModificarLuchador());
                fragmentTransaction.commit();
                break;
        }
        return true;
    }
}
