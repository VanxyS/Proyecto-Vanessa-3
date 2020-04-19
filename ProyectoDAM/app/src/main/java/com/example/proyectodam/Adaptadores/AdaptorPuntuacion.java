package com.example.proyectodam.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectodam.Items.ItemPuntuacion;
import com.example.proyectodam.R;

import java.util.ArrayList;

public class AdaptorPuntuacion extends BaseAdapter
{
    private Context context;
    private ArrayList<ItemPuntuacion> items;

    public AdaptorPuntuacion(Context context, ArrayList<ItemPuntuacion> items) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemPuntuacion item = (ItemPuntuacion) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_puntuacion, null);
        ImageView imageView = convertView.findViewById(R.id.viewFoto);
        TextView textView = convertView.findViewById(R.id.tituloPuntuacion);
        TextView textView1 = convertView.findViewById(R.id.subtituloPuntuacion);
        TextView textView2 = convertView.findViewById(R.id.puntuacion);

        imageView.setImageResource(item.getImagen());
        textView.setText(item.getTituloPuntuacion());
        textView1.setText(item.getSubtituloPuntuacion());
        textView2.setText(item.getPuntuacion());

        return convertView;
    }
}
