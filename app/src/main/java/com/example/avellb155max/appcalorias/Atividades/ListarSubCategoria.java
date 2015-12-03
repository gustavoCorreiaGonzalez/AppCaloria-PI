package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.avellb155max.appcalorias.Adapter.SubCategoria;
import com.example.avellb155max.appcalorias.Adapter.SubCategoriaAdapter;
import com.example.avellb155max.appcalorias.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListarSubCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_padrao);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // pegando os parametros passados para a atividade
        Intent intent = getIntent();
        final String idDiario = intent.getStringExtra("idDiario");
        final String idTipo = intent.getStringExtra("idTipo");
        final String idCategoriaIntent = intent.getStringExtra("idCategoria");

        System.out.println("VINDOOOOO idCategoria: " + idCategoriaIntent);

        final List<SubCategoria> SubCategorias = new ArrayList<SubCategoria>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("subCategorias");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String idCategoria = jo_inside.getString("idCategoria");
                String id = jo_inside.getString("id");
                String nome = jo_inside.getString("nome");

                System.out.println("idCategoria JSOMMMM: " + idCategoria);

                if(idCategoriaIntent.equals(idCategoria)) {
                    SubCategorias.add(new SubCategoria(idCategoria, id, nome));
                }

                System.out.println("id: " + id);
                System.out.println("nome: " + nome);
                m_li = new HashMap<String, String>();
                m_li.put("idCategoria", idCategoria);
                m_li.put("id", id);
                m_li.put("nome", nome);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SubCategoriaAdapter adapter = new SubCategoriaAdapter(this, R.layout.activity_item_padrao, SubCategorias);
        ListView Lista = (ListView) findViewById(R.id.listaElementos);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                SubCategoria subCategoria = SubCategorias.get(position);

                // Passa o ID da categoria para listar as SubCategorias
                Intent intent = new Intent(v.getContext(), ListarAlimentos.class);
                Bundle params = new Bundle();

                params.putString("idDiario", idDiario);
                params.putString("idTipo", idTipo);
                params.putString("idCategoriaIntent", idCategoriaIntent);
                params.putString("idSubCategoria", String.valueOf(subCategoria.getId()));

                System.out.println("INDOOOO idCategoria: " + position);
                intent.putExtras(params);
                startActivity(intent);
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("subCategorias.json");
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