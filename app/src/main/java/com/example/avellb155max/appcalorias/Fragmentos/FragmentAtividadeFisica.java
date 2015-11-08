package com.example.avellb155max.appcalorias.Fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avellb155max.appcalorias.R;

/**
 * Created by gustavo on 24/10/15.
 */
public class FragmentAtividadeFisica extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atividade_fisica,
                container, false);
    }
}
