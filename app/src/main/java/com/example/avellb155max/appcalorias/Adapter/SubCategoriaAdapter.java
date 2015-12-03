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
public class SubCategoriaAdapter extends ArrayAdapter<SubCategoria> {
    Context context;
    int resource;
    List<SubCategoria> subCategorias = new ArrayList<>();

    public SubCategoriaAdapter(Context context, int resource, List<SubCategoria> subCategorias) {
        super(context, resource, subCategorias);
        this.resource = resource;
        this.context = context;
        this.subCategorias = subCategorias;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        SubCategoria item = subCategorias.get(position);

        if(item != null){
            TextView id = (TextView) convertView.findViewById(R.id.idElemento);
            TextView nome = (TextView) convertView.findViewById(R.id.nomeElemento);

            id.setText(item.getId());
            nome.setText(item.getNome());
        }
        return convertView;
    }
}