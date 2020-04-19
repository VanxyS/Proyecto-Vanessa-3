package com.example.proyectodam;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectodam.Fragments.LigerosFragment;
import com.example.proyectodam.Fragments.LigerosFragmentPuntuacion;
import com.example.proyectodam.Fragments.MediosFragment;
import com.example.proyectodam.Fragments.MediosFragmentPuntuacion;
import com.example.proyectodam.Fragments.PesadosFragment;
import com.example.proyectodam.Fragments.PesadosFragmentPuntuacion;

public class PagerAdapterPuntuacion extends FragmentPagerAdapter
{
    private int numTabs;

    public PagerAdapterPuntuacion(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new LigerosFragmentPuntuacion();
            case 1:
                return new MediosFragmentPuntuacion();
            case 2:
                return new PesadosFragmentPuntuacion();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
