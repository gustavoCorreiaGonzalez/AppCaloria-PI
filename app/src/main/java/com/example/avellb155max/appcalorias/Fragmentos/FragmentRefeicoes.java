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

import com.example.avellb155max.appcalorias.Atividades.ListarAtividades;
import com.example.avellb155max.appcalorias.Atividades.ListarExercicios;
import com.example.avellb155max.appcalorias.Atividades.ListarCategoria;
import com.example.avellb155max.appcalorias.Classes.ItensDiario;
import com.example.avellb155max.appcalorias.R;

import java.util.List;

/**
 * Created by gustavo on 24/10/15.
 */
public class FragmentRefeicoes extends Fragment{
    public long idDiario;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

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

        // Café da Manhã
        button1 = (Button) rootView.findViewById(R.id.buttonAlimentos1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividades.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(1));
                intent.putExtras(params);
                startActivity(intent);
            }
        });

        // Almoço
        button2 = (Button) rootView.findViewById(R.id.buttonAlimentos2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividades.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(2));
                intent.putExtras(params);
                startActivity(intent);
            }
        });

        // Janta
        button3 = (Button) rootView.findViewById(R.id.buttonAlimentos3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividades.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(3));
                intent.putExtras(params);
                startActivity(intent);
            }
        });

        // Lanches
        button4 = (Button) rootView.findViewById(R.id.buttonAlimentos4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividades.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(4));
                intent.putExtras(params);
                startActivity(intent);
            }
        });

        // Exercícios
        button5 = (Button) rootView.findViewById(R.id.buttonAlimentos5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListarAtividades.class);
                Bundle params = new Bundle();

                params.putString("idDiario", String.valueOf(idDiario));
                params.putString("idTipo", String.valueOf(5));
                intent.putExtras(params);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
