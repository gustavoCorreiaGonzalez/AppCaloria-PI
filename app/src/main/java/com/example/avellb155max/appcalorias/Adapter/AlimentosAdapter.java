package com.example.avellb155max.appcalorias.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.avellb155max.appcalorias.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 07/11/15.
 */
public class AlimentosAdapter extends ArrayAdapter<Alimentos>{
    Context context;
    int resource;
    List<Alimentos> alimentos = new ArrayList<>();

    public AlimentosAdapter(Context context, int resource, List<Alimentos> alimentos) {
        super(context, resource, alimentos);
        this.resource = resource;
        this.context = context;
        this.alimentos = alimentos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Alimentos item = alimentos.get(position);

        if(item != null){
            TextView id = (TextView) convertView.findViewById(R.id.alimentoId);
            TextView nome = (TextView) convertView.findViewById(R.id.alimentoNome);
            TextView caloria = (TextView) convertView.findViewById(R.id.alimentoCaloria);

            id.setText(item.getId());
            nome.setText(item.getNome());
            caloria.setText(item.getCaloria());
        }
        return convertView;
    }
}
