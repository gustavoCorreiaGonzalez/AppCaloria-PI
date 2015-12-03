package com.example.avellb155max.appcalorias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avellb155max.appcalorias.Atividades.ListarCategoria;
import com.example.avellb155max.appcalorias.Classes.DadosUsuario;

public class CadastrarDadosPessoais extends AppCompatActivity {
    public Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dados_pessoais);

        cadastrar = (Button) findViewById(R.id.cadastrarDadosPessoais);
        final EditText peso = (EditText) findViewById((R.id.peso));
        final EditText altura = (EditText) findViewById((R.id.altura));
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DadosUsuario dadosUsuario = new DadosUsuario(peso.getText().toString(),altura.getText().toString());
                dadosUsuario.save();

                // redireciona para o app
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
