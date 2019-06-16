package com.unab.camerica.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unab.camerica.R;
import com.unab.camerica.models.Partido;

public class PartidoAdapter extends ArrayAdapter<Partido> {

    public PartidoAdapter(Context context, Partido[] data) {
        super(context, R.layout.partido, data);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Partido actual = this.getItem(position);
        View item = convertView;

        PartidoHolder holder;

        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.partido, null);
            holder = new PartidoHolder();
            holder.codLocal = item.findViewById(R.id.cod_local);
            holder.banderaLocal = item.findViewById(R.id.bandera_local);
            holder.codVisita = item.findViewById(R.id.cod_visita);
            holder.banderaVisita = item.findViewById(R.id.bandera_visita);
            holder.fecha = item.findViewById(R.id.fecha);
            holder.hora = item.findViewById(R.id.hora);

            item.setTag(holder);
        } else {
            holder = (PartidoHolder)item.getTag();
        }

        // Completar holder
        holder.codLocal.setText(actual.getLocal().getCodigo());
        holder.banderaLocal.setText(actual.getLocal().getBandera());
        holder.codVisita.setText(actual.getVisita().getCodigo());
        holder.banderaVisita.setText(actual.getVisita().getBandera());
        holder.fecha.setText(actual.getFecha());
        holder.hora.setText(actual.getHora());

        return item;
    }

    private class PartidoHolder {
        TextView codLocal;
        TextView banderaLocal;
        TextView codVisita;
        TextView banderaVisita;
        TextView hora;
        TextView fecha;
    }
}