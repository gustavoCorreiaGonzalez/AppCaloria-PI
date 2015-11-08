package com.example.avellb155max.appcalorias.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avellb155max.appcalorias.R;

/**
 * Created by gustavo on 24/10/15.
 */
public class FragmentRefeicoes extends Fragment {
    private long idDiario;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refeicoes, container, false);
    }
}
