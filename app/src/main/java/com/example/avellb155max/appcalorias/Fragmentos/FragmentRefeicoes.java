package com.example.avellb155max.appcalorias.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.avellb155max.appcalorias.Atividades.ListarCategoria;
import com.example.avellb155max.appcalorias.R;

/**
 * Created by gustavo on 24/10/15.
 */
public class FragmentRefeicoes extends Fragment{
    private long idDiario;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

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

        button1 = (Button) rootView.findViewById(R.id.buttonAlimentos1);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListarCategoria.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
