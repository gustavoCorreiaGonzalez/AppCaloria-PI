package com.example.avellb155max.appcalorias.Adapter;

/**
 * Created by gustavo on 10/11/15.
 */
public class SubCategoria {
    public String idCategora;
    public String id;
    public String nome;

    public SubCategoria(String idCategora, String id, String nome) {
        this.idCategora = idCategora;
        this.id = id;
        this.nome = nome;
    }

    public String getIdCategora() {
        return idCategora;
    }

    public void setIdCategora(String idCategora) {
        this.idCategora = idCategora;
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
