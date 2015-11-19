package com.example.avellb155max.appcalorias.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.avellb155max.appcalorias.Classes.DadosUsuario;
import com.example.avellb155max.appcalorias.R;

/**
 * Created by Paulo on 18/11/2015.
 */

public class FragmentDadosPessoais extends Fragment {
    private TextView imc;
    private EditText peso;
    private EditText altura;
    private float valorImc;

    public static FragmentDadosPessoais newInstance(String param1, String param2) {
        FragmentDadosPessoais fragment = new FragmentDadosPessoais();

        return fragment;
    }

    public FragmentDadosPessoais()  {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dados_pessoais, container, false);

        DadosUsuario dados = DadosUsuario.findById(DadosUsuario.class, (long) 1);

        peso = (EditText) rootView.findViewById(R.id.peso);
        peso.setText(dados.getPeso());

        altura = (EditText) rootView.findViewById(R.id.altura);
        altura.setText(dados.getAltura());

        imc = (TextView) rootView.findViewById(R.id.imc);
        imc.setText(this.getValorImc(dados.getPeso(),dados.getAltura()).toString());

        return rootView;
    }

    public Double getValorImc(String p, String a) {
        Double imc;
        Double peso;
        Double altura;

        peso = Double.parseDouble(p);
        altura = Double.parseDouble(a);

        imc = peso / (Math.pow(altura,2));

        return imc;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
