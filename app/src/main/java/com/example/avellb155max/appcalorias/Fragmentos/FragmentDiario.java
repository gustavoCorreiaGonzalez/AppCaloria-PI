package com.example.avellb155max.appcalorias.Fragmentos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avellb155max.appcalorias.Adapter.AtividadeFisica;
import com.example.avellb155max.appcalorias.Classes.Diario;
import com.example.avellb155max.appcalorias.Classes.ItensDiario;
import com.example.avellb155max.appcalorias.R;
import com.example.avellb155max.appcalorias.Utils.Utils;

import java.util.Date;
import java.util.List;

/**
 * Created by gustavo on 24/10/15.
 */

public class FragmentDiario extends Fragment {
    private ListView listView;
    private double somarCalorias;

    public static FragmentDiario newInstance(String param1, String param2) {
        FragmentDiario fragment = new FragmentDiario();

        return fragment;
    }

    public FragmentDiario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_diario, container, false);

        listView = (ListView) rootView.findViewById(R.id.listViewDiario);

        // Adiciona
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Diario diario = ((DiarioAdapter) listView.getAdapter()).getItem(position);

                getActivity().getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, FragmentRefeicoes.newInstance(diario.getId())).addToBackStack(null)
                        .commit();

                //atualizarLista
                updateListContent();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Diario diario = ((DiarioAdapter) listView.getAdapter()).getItem(position);
                removerDiario(diario.getId());
                return true;
            }
        });

        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.addDia);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addDia();
            }
        });

        return rootView;
    }

    // Função que roda ao iniciar o fragmento
    @Override
    public void onStart() {
        super.onStart();

        // atualiza lista
        updateListContent();
    }

    public void addDia() {
        Date data = new Date();
        Diario diario = new Diario(0,0,0,data,null);
        diario.save();

        updateListContent();
    }


    // Função que atualiza a lista do Diário
    private void updateListContent() {
        List<Diario> items = Diario.findWithQuery(Diario.class, "SELECT * FROM Diario");

        for(Diario d : items){
            d.setCaloriasRestantes( d.getCaloriasConsumidas() - d.getCaloriasQueimadas());
            d.save();
        }
        DiarioAdapter adapter = new DiarioAdapter(getActivity(), items);
        listView.setAdapter(adapter);
    }

    //private void

    // Função que remove o dia de treino
    public void removerDiario(final long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remover esse dia de treino?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Diario diario = Diario.findById(Diario.class, id);
                diario.delete();
                Toast.makeText(getActivity(), "O treino foi removido!", Toast.LENGTH_SHORT).show();

                updateListContent();

                listView.invalidateViews();
            }
        })
        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setIcon(android.R.drawable.ic_delete).show();
    }

    // adapter para o conteudo da ListView
    private class DiarioAdapter extends ArrayAdapter<Diario> {
        public DiarioAdapter(Context context, List<Diario> items) {
            super(context, android.R.layout.simple_list_item_1, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // se nao recebermos uma view, infla uma
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_diario_itens, null);
            }

            final Diario diario = getItem(position);

            TextView data = (TextView) convertView.findViewById(R.id.textView_data);
            data.setText(String.valueOf(Utils.formatDateDia(diario.getData())));

            TextView consumidas = (TextView) convertView.findViewById(R.id.textViewConsumidas);
            consumidas.setText(String.valueOf(diario.getCaloriasConsumidas()));

            TextView restantes = (TextView) convertView.findViewById(R.id.textViewRestantes);
            restantes.setText(String.valueOf(diario.getCaloriasRestantes()));

            TextView queimadas = (TextView) convertView.findViewById(R.id.textViewQueimadas);
            queimadas.setText(String.valueOf(diario.getCaloriasQueimadas()));

            return convertView;
        }
    }
}
