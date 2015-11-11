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
 * Created by gustavo on 10/11/15.
 */
public class CategoriaAdapter extends  ArrayAdapter<Categoria>{
    Context context;
    int resource;
    List<Categoria> categoria = new ArrayList<>();

    public CategoriaAdapter(Context context, int resource, List<Categoria> categoria) {
        super(context, resource, categoria);
        this.resource = resource;
        this.context = context;
        this.categoria = categoria;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Categoria item = categoria.get(position);

        if(item != null){
            TextView id = (TextView) convertView.findViewById(R.id.categoriaId);
            TextView nome = (TextView) convertView.findViewById(R.id.categoriaNome);

            id.setText(item.getId());
            nome.setText(item.getNome());
        }
        return convertView;
    }
}
