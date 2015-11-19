package com.example.avellb155max.appcalorias.Atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avellb155max.appcalorias.Adapter.Alimentos;
import com.example.avellb155max.appcalorias.Adapter.AlimentosAdapter;
import com.example.avellb155max.appcalorias.Adapter.Categoria;
import com.example.avellb155max.appcalorias.Adapter.CategoriaAdapter;
import com.example.avellb155max.appcalorias.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListarAlimentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alimentos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String idCategoriaIntent = intent.getStringExtra("idCategoria");

        final List<Alimentos> Alimentos = new ArrayList<Alimentos>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("alimentos");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String idCategoria = jo_inside.getString("idCategoria");
                String idSubCategoria = jo_inside.getString("idSubCategoria");
                String id = jo_inside.getString("id");
                String nome = jo_inside.getString("nome");
                String caloria = jo_inside.getString("caloria");

                if(idCategoriaIntent.equals(idCategoria)) {
                    Alimentos.add(new Alimentos(idCategoria, idSubCategoria, id, nome, caloria));
                }

                m_li = new HashMap<String, String>();
                m_li.put("idCategoria", idCategoria);
                m_li.put("idSubCategoria", idSubCategoria);
                m_li.put("id", id);
                m_li.put("nome", nome);
                m_li.put("caloria", caloria);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AlimentosAdapter adapter = new AlimentosAdapter(this, R.layout.activity_listar_alimentos_item, Alimentos);
        ListView Lista = (ListView) findViewById(R.id.listaAlimentos);
        Lista.setAdapter(adapter);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Alimentos alimentos = Alimentos.get(position);

                TextView nome = (TextView) findViewById(R.id.alimentoNome);
                TextView caloria = (TextView) findViewById(R.id.alimentoCaloria);

                //Toast.makeText(, "O treino foi removido!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("alimentos.json");
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
