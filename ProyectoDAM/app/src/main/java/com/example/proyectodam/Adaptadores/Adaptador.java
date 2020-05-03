package com.example.proyectodam.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectodam.Items.Item;
import com.example.proyectodam.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter
{
    private Context context;
    private ArrayList<Item> items;

    public Adaptador(Context context, ArrayList<Item> items) {
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
        Item item = (Item) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        ImageView imageView = convertView.findViewById(R.id.viewFoto);
        TextView textView = convertView.findViewById(R.id.titulo);
        TextView textView1 = convertView.findViewById(R.id.subtitulo);

        imageView.setImageResource(item.getImagen());
        textView.setText(item.getTitulo());
        textView1.setText(item.getSubtitulo() + " " + item.getApellido2());

        return convertView;
    }
}
