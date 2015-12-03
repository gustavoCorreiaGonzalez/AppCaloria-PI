package com.example.avellb155max.appcalorias.Atividades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.avellb155max.appcalorias.Classes.ItensDiario;
import com.example.avellb155max.appcalorias.R;

import java.util.List;

public class ListarAtividades extends AppCompatActivity {
    String idDiario;
    String idTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividades);

        // pegando os parametros passados para a atividade
        Intent intent = getIntent();
        idDiario = intent.getStringExtra("idDiario");
        idTipo = intent.getStringExtra("idTipo");

        TextView textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        if(idTipo.equals("1")) {
            textViewTitulo.setText("Café da manhã");
        } else if(idTipo.equals("2")) {
            textViewTitulo.setText("Almoço");
        } else if(idTipo.equals("3")) {
            textViewTitulo.setText("Janta");
        } else if(idTipo.equals("4")) {
            textViewTitulo.setText("Lanches");
        } else if(idTipo.equals("5")) {
            textViewTitulo.setText("Exercícios");
        }

        ListView list = (ListView) findViewById(R.id.listViewAtividade);
        List<ItensDiario> itens = ItensDiario.find(ItensDiario.class, "diario = ? AND tipo = ?", idDiario, idTipo);
        AtividadesAdapter adapter = new AtividadesAdapter(this, itens);
        list.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.buttonAdicionarAtividade);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListarCategoria.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(idTipo));
                intent.putExtras(params);
                startActivity(intent);
            }
        });
    }

    private class AtividadesAdapter extends ArrayAdapter<ItensDiario> {

        public AtividadesAdapter(Context context, List<ItensDiario> items) {
            super(context, android.R.layout.simple_list_item_1, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // se nao recebermos uma view, infla uma
            if (null == convertView) {
                convertView = getLayoutInflater().inflate(R.layout.activity_listar_alimentos_item, null);
            }

            ItensDiario item = getItem(position);

            TextView id = (TextView) convertView.findViewById(R.id.alimentoId);
            id.setText(String.valueOf(item.getAtividadesDiarias().getId()));

            TextView nome = (TextView) convertView.findViewById(R.id.alimentoNome);
            nome.setText(String.valueOf(item.getAtividadesDiarias().getNome()));

            TextView caloria = (TextView) convertView.findViewById(R.id.alimentoCaloria);
            caloria.setText(String.valueOf(item.getAtividadesDiarias().getCaloria()));

            return convertView;
        }
    }
}
