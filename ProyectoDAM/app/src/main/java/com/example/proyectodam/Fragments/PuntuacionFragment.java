package com.example.proyectodam.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proyectodam.PagerAdapter;
import com.example.proyectodam.PagerAdapterPuntuacion;
import com.example.proyectodam.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class PuntuacionFragment extends Fragment
{
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    ListView ligeros, medios, pesados;

    public PuntuacionFragment(DrawerLayout drawer, FragmentManager fragmentManager, ActionBarDrawerToggle actionBarDrawerToggle, NavigationView navigationView, Toolbar toolbar)
    {
        this.drawerLayout = drawer;
        this.actionBarDrawerToggle = actionBarDrawerToggle;
        this.fragmentManager = fragmentManager;
        this.navigationView = navigationView;
        this.toolbar = toolbar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_puntuacion, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tabPuntuacion);
        final ViewPager pagerPuntuacion = view.findViewById(R.id.viewPagerPuntuacion);
        ligeros = view.findViewById(R.id.ligerosPuntuacion);
        medios = view.findViewById(R.id.mediosPuntuacion);
        pesados = view.findViewById(R.id.pesadosPuntuacion);

        //pagerPuntuaciones.setAdapter(pagerAdapter);

        PagerAdapterPuntuacion pagerAdapter = new PagerAdapterPuntuacion(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        pagerPuntuacion.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerPuntuacion.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pagerPuntuacion.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }
}
