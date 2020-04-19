package com.example.proyectodam.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectodam.Items.ItemCorro;
import com.example.proyectodam.R;

import java.util.ArrayList;

public class AdaptadorCorros extends BaseAdapter
{
    private Context context;
    private ArrayList<ItemCorro> items;

    public AdaptadorCorros(Context context, ArrayList<ItemCorro> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemCorro item = (ItemCorro) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_corro, null);
        ImageView imageView = convertView.findViewById(R.id.viewFoto);
        TextView textView = convertView.findViewById(R.id.nombrePueblo);
        TextView textView1 = convertView.findViewById(R.id.diaAniadir);
        TextView textView2 = convertView.findViewById(R.id.horaAniadir);
        TextView textView3 = convertView.findViewById(R.id.lugar);

        imageView.setImageResource(item.getImagen());
        textView.setText(item.getPueblo());
        textView1.setText(item.getDia());
        textView2.setText(item.getHora());
        textView3.setText(item.getLugar());

        return convertView;
    }
}
