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
 * Created by gustavo on 11/11/15.
 */
public class AtividadeFisicaAdapter extends ArrayAdapter<AtividadeFisica> {
    Context context;
    int resource;
    List<AtividadeFisica> atividadeFisica = new ArrayList<>();

    public AtividadeFisicaAdapter(Context context, int resource, List<AtividadeFisica> atividadeFisica) {
        super(context, resource, atividadeFisica);
        this.resource = resource;
        this.context = context;
        this.atividadeFisica = atividadeFisica;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        AtividadeFisica item = atividadeFisica.get(position);

        if(item != null){
            TextView id = (TextView) convertView.findViewById(R.id.textViewExercicio_id);
            TextView nome = (TextView) convertView.findViewById(R.id.textViewExercicio_nome);
            TextView caloria = (TextView) convertView.findViewById(R.id.textViewExercicio_caloria);
            TextView tempo = (TextView) convertView.findViewById(R.id.textViewExercicio_tempo);

            id.setText(item.getId());
            nome.setText(item.getNome());
            caloria.setText(item.getCaloria());
            tempo.setText(item.getTempo());
        }
        return convertView;
    }
}