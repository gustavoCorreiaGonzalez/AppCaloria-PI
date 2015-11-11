package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        setContentView(R.layout.activity_listar_sub_categoria);

        Intent intent = getIntent();
        String idCategoriaIntent = intent.getStringExtra("idCategoria");

        System.out.println("VINDOOOOO idCategoria: " + idCategoriaIntent);

        List<SubCategoria> subCategorias = new ArrayList<SubCategoria>();

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
                    subCategorias.add(new SubCategoria(idCategoria, id, nome));
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

        SubCategoriaAdapter adapter = new SubCategoriaAdapter(this, R.layout.activity_sub_categoria_item, subCategorias);
        ListView Lista = (ListView) findViewById(R.id.listaSubCategoria);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Passa o ID da categoria para listar as SubCategorias
                Intent intent = new Intent(v.getContext(), ListarAlimentos.class);
                Bundle params = new Bundle();

                params.putString("idCategoria", String.valueOf(position+1));
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