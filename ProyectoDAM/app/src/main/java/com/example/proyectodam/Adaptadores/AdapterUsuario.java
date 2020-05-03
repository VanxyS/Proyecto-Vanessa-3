package com.example.proyectodam.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam.Items.Item;
import com.example.proyectodam.Items.Usuario;
import com.example.proyectodam.R;

import java.util.ArrayList;

public class AdapterUsuario extends BaseAdapter
{
    private Context context;
    private ArrayList<Usuario> items;

    public AdapterUsuario(Context context, ArrayList<Usuario> items) {
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

    private class ViewHolder
    {
        TextView usuario;
        TextView rol;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

            convertView = LayoutInflater.from(context).inflate(R.layout.listview_row, null);
            holder = new ViewHolder();

            holder.usuario = convertView.findViewById(R.id.usuarioMostrar);
            holder.rol = convertView.findViewById(R.id.rolMostrar);

        Usuario usuario = items.get(position);
        holder.usuario.setText(usuario.getUsuario().toString());
        holder.rol.setText(usuario.getRol().toString());

        return convertView;
    }
}
