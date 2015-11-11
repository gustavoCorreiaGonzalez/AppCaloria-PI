package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.avellb155max.appcalorias.Adapter.AtividadeFisica;
import com.example.avellb155max.appcalorias.Adapter.AtividadeFisicaAdapter;
import com.example.avellb155max.appcalorias.Classes.Diario;
import com.example.avellb155max.appcalorias.Model.AtividadesDiarias;
import com.example.avellb155max.appcalorias.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListarAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividade);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String idTipo = intent.getStringExtra("idTipo");

        final List<AtividadeFisica> atividade = new ArrayList<AtividadeFisica>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Exercicios");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String nome = jo_inside.getString("nomeDoExercicio");
                String caloria = jo_inside.getString("caloriaGasta");
                String tempo = jo_inside.getString("tempoDeExercicio");

                atividade.add(new AtividadeFisica(id, nome, caloria, tempo));
                m_li = new HashMap<String, String>();
                m_li.put("id", id);
                m_li.put("nomeDoExercicio", nome);
                m_li.put("caloriaGasta", caloria);
                m_li.put("tempoDeExercicio", tempo);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AtividadeFisicaAdapter adapter = new AtividadeFisicaAdapter(this, R.layout.activity_listar_atividade_item, atividade);
        ListView Lista = (ListView) findViewById(R.id.listaAtividades);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                AtividadesDiarias atividadesDiarias = new AtividadesDiarias("BIKE",300,Integer.parseInt(idTipo));
                atividadesDiarias.save();

                AtividadesDiarias atividadesDiarias1 = AtividadesDiarias.findById(AtividadesDiarias.class, (long) 1);

                Diario diario = new Diario();

                diario.AtualizarCalorias(atividadesDiarias1.getId());
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("exercicios.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}

