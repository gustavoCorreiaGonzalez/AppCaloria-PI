package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.avellb155max.appcalorias.Adapter.Categoria;
import com.example.avellb155max.appcalorias.Adapter.CategoriaAdapter;
import com.example.avellb155max.appcalorias.Adapter.SubCategoria;
import com.example.avellb155max.appcalorias.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListarCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Categoria> categoria = new ArrayList<Categoria>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("categorias");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String nome = jo_inside.getString("nome");

                categoria.add(new Categoria(id, nome));
                System.out.println("id: " + id);
                System.out.println("nome: " + nome);
                m_li = new HashMap<String, String>();
                m_li.put("id", id);
                m_li.put("nome", nome);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CategoriaAdapter adapter = new CategoriaAdapter(this, R.layout.activity_categoria_item, categoria);
        ListView Lista = (ListView) findViewById(R.id.listaCategoria);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent i = new Intent(ListarCategoria.this, ListarSubCategoria.class);
                startActivity(i);
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("categorias.json");
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
