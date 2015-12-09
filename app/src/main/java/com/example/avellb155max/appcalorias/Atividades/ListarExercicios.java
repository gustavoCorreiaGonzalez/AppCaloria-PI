package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.avellb155max.appcalorias.Adapter.AtividadeFisica;
import com.example.avellb155max.appcalorias.Adapter.AtividadeFisicaAdapter;
import com.example.avellb155max.appcalorias.Classes.AtividadesDiarias;
import com.example.avellb155max.appcalorias.Classes.Diario;
import com.example.avellb155max.appcalorias.Classes.ItensDiario;
import com.example.avellb155max.appcalorias.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListarExercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_padrao);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String idDiario = intent.getStringExtra("idDiario");
        final String idTipo = intent.getStringExtra("idTipo");

        final List<AtividadeFisica> Atividade = new ArrayList<AtividadeFisica>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Exercicios");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String nome = jo_inside.getString("nome");
                String caloria = jo_inside.getString("caloria");
                String tempo = jo_inside.getString("tempo");

                Atividade.add(new AtividadeFisica(id, nome, caloria, tempo));
                m_li = new HashMap<String, String>();
                m_li.put("id", id);
                m_li.put("nome", nome);
                m_li.put("caloria", caloria);
                m_li.put("tempo", tempo);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AtividadeFisicaAdapter adapter = new AtividadeFisicaAdapter(this, R.layout.activity_listar_atividade_item, Atividade);
        ListView Lista = (ListView) findViewById(R.id.listaElementos);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                int soma = 0;
                AtividadeFisica atividadeFisica = Atividade.get(position);

                AtividadesDiarias atividadesDiarias = new AtividadesDiarias(atividadeFisica.getNome(),Integer.parseInt(atividadeFisica.getCaloria()), Integer.parseInt(idTipo));
                atividadesDiarias.save();

                Diario diario = Diario.findById(Diario.class, Long.valueOf(idDiario));
                //diario.se

                ItensDiario itensDiario = new ItensDiario(diario,atividadesDiarias,Integer.valueOf(idTipo));
                itensDiario.save();

                soma = diario.getCaloriasQueimadas() + Integer.parseInt(String.valueOf(atividadeFisica.getCaloria()));
                diario.setCaloriasQueimadas(soma);
                System.out.println("soma + " + soma);
                diario.save();

                Toast.makeText(ListarExercicios.this, "Exercício adicionado !!" + atividadeFisica.getNome(), Toast.LENGTH_SHORT).show();
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

