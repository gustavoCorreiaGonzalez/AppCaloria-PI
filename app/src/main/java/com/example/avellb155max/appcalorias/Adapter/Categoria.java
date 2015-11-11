package com.example.avellb155max.appcalorias.Adapter;

/**
 * Created by gustavo on 10/11/15.
 */
public class Categoria {
    public String id;
    public String nome;

    public Categoria(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
