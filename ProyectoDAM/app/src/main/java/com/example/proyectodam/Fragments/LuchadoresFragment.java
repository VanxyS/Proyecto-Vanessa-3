package com.example.proyectodam.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.proyectodam.APIMethods;
import com.example.proyectodam.Drawer;
import com.example.proyectodam.PagerAdapter;
import com.example.proyectodam.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LuchadoresFragment extends Fragment
{
    ListView ligeros, medios, pesados;

//    public LuchadoresFragment(DrawerLayout drawer, FragmentManager fragmentManager, ActionBarDrawerToggle actionBarDrawerToggle, NavigationView navigationView, Toolbar toolbar)
//    {
//        this.drawerLayout = drawer;
//        this.actionBarDrawerToggle = actionBarDrawerToggle;
//        this.fragmentManager = fragmentManager;
//        this.navigationView = navigationView;
//        this.toolbar = toolbar;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.luchadores_fragment, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tabLuchadores);
        final ViewPager pagerLuchadores = view.findViewById(R.id.viewPager);
        ligeros = view.findViewById(R.id.ligerosLuchadores);
        medios = view.findViewById(R.id.mediosLuchadores);
        pesados = view.findViewById(R.id.pesadosLuchadores);

        //pagerPuntuaciones.setAdapter(pagerAdapter);

        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        pagerLuchadores.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerLuchadores.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pagerLuchadores.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }
}
