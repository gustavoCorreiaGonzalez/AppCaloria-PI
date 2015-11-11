package com.example.avellb155max.appcalorias.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.avellb155max.appcalorias.Atividades.ListarAtividade;
import com.example.avellb155max.appcalorias.Atividades.ListarCategoria;
import com.example.avellb155max.appcalorias.CadastrarDadosPessoais;
import com.example.avellb155max.appcalorias.Model.AtividadesDiarias;
import com.example.avellb155max.appcalorias.R;

import java.sql.CallableStatement;
import java.util.List;

/**
 * Created by gustavo on 24/10/15.
 */
public class FragmentRefeicoes extends Fragment{
    private long idDiario;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ListView listView5;

    public static FragmentRefeicoes newInstance(final long idDiario) {
        FragmentRefeicoes fragment = new FragmentRefeicoes();

        Bundle itens = new Bundle();
        itens.putLong("idDiario", (int) idDiario);
        fragment.setArguments(itens);

        return fragment;
    }

    public FragmentRefeicoes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            idDiario = getArguments().getLong("idDiario");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_refeicoes, container, false);


        listView5 = (ListView) rootView.findViewById(R.id.listExecicios);

        List<AtividadesDiarias> items = AtividadesDiarias.listAll(AtividadesDiarias.class);

        RefeicoesAdapter adapter = new RefeicoesAdapter(getActivity(), items);
        listView5.setAdapter(adapter);
        
        button1 = (Button) rootView.findViewById(R.id.buttonAlimentos1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        button2 = (Button) rootView.findViewById(R.id.buttonAlimentos2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        button3 = (Button) rootView.findViewById(R.id.buttonAlimentos3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        button3 = (Button) rootView.findViewById(R.id.buttonAlimentos3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        button4 = (Button) rootView.findViewById(R.id.buttonAlimentos4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        button5 = (Button) rootView.findViewById(R.id.buttonAlimentos5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividade.class);
                Bundle params = new Bundle();

                params.putString("idTipo", String.valueOf(5));
                intent.putExtras(params);
                startActivity(intent);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    private class RefeicoesAdapter extends ArrayAdapter<AtividadesDiarias> {

        public RefeicoesAdapter(Context context, List<AtividadesDiarias> items) {
            super(context, android.R.layout.simple_list_item_1, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // se nao recebermos uma view, infla uma
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_refeicoes_itens, null);
            }

            AtividadesDiarias atividadesDiarias = getItem(position);

            TextView id = (TextView) convertView.findViewById(R.id.idListaRefeicoes);
            id.setText(String.valueOf(atividadesDiarias.getId()));

            TextView nome = (TextView) convertView.findViewById(R.id.refeicoesNome);
            nome.setText(String.valueOf(atividadesDiarias.getNome()));

            TextView caloria = (TextView) convertView.findViewById(R.id.refeicoesCalorias);
            caloria.setText(String.valueOf(atividadesDiarias.getCaloria()));

            return convertView;
        }
    }
}
