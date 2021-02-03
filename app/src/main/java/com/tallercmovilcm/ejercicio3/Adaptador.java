package com.tallercmovilcm.ejercicio3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tallercmovilcm.ejercicio3.model.Pokemon;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter
{

    Context contexto;
    ArrayList<Pokemon> pokemons;
    private static LayoutInflater inflater = null;

    public Adaptador(Context contexto, ArrayList<Pokemon> pokemons)
    {
        this.contexto = contexto;
        this.pokemons = pokemons;

        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position)
    {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return pokemons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView tvNombre = vista.findViewById(R.id.tvNombre);
        TextView tvId = vista.findViewById(R.id.tvId);

        tvNombre.setText(pokemons.get(position).getNombre());
        tvId.setText("#"+String.valueOf(pokemons.get(position).getId()+1));


        return vista;
    }
}