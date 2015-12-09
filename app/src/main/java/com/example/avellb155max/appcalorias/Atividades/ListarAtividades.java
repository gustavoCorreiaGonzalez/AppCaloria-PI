package com.example.avellb155max.appcalorias.Atividades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avellb155max.appcalorias.Adapter.AtividadeFisica;
import com.example.avellb155max.appcalorias.Adapter.AtividadeFisicaAdapter;
import com.example.avellb155max.appcalorias.Classes.AtividadesDiarias;
import com.example.avellb155max.appcalorias.Classes.Diario;
import com.example.avellb155max.appcalorias.Classes.ItensDiario;
import com.example.avellb155max.appcalorias.R;

import java.util.List;

public class ListarAtividades extends AppCompatActivity {
    String idDiario;
    String idTipo;
    String idAtividade;
    ListView list;

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

        list = (ListView) findViewById(R.id.listViewAtividade);
        List<ItensDiario> itens = ItensDiario.find(ItensDiario.class, "diario = ? AND tipo = ?", idDiario, idTipo);
        AtividadesAdapter adapter = new AtividadesAdapter(this, itens);
        list.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.buttonAdicionarAtividade);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!idTipo.equals("5")) {
                    Intent intent = new Intent(v.getContext(), ListarCategoria.class);
                    Bundle params = new Bundle();

                    params.putString("idDiario", String.valueOf(idDiario));
                    params.putString("idTipo", String.valueOf(idTipo));
                    intent.putExtras(params);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getContext(), ListarExercicios.class);
                    Bundle params = new Bundle();

                    params.putString("idDiario", String.valueOf(idDiario));
                    params.putString("idTipo", String.valueOf(idTipo));
                    intent.putExtras(params);
                    startActivity(intent);
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ItensDiario itensDiario = ((AtividadesAdapter) list.getAdapter()).getItem(position);
                removerAtividade(itensDiario.getId());
                return true;
            }
        });

    }

    // Função que atualiza a lista da Atividade
    private void updateListContent() {
        List<ItensDiario> items = ItensDiario.findWithQuery(ItensDiario.class, "SELECT * FROM Itens_Diario");

        AtividadesAdapter adapter = new AtividadesAdapter(getApplicationContext(), items);
        list.setAdapter(adapter);
    }

    // Função que remove a atividade
    public void removerAtividade(final Long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover essa atividade física?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ItensDiario itensDiario = ItensDiario.findById(ItensDiario.class, Long.valueOf(id));
                itensDiario.delete();
                Toast.makeText(getApplicationContext(), "A atividade foi removida!", Toast.LENGTH_SHORT).show();

                updateListContent();

                list.invalidateViews();
            }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            }).setIcon(android.R.drawable.ic_delete).show();
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
            idAtividade = item.getAtividadesDiarias().getId().toString();
            id.setText(String.valueOf(item.getAtividadesDiarias().getId()));

            TextView nome = (TextView) convertView.findViewById(R.id.alimentoNome);
            nome.setText(String.valueOf(item.getAtividadesDiarias().getNome()));

            TextView caloria = (TextView) convertView.findViewById(R.id.alimentoCaloria);
            caloria.setText(String.valueOf(item.getAtividadesDiarias().getCaloria()));

            return convertView;
        }
    }
}
