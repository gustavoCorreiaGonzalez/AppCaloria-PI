package com.example.avellb155max.appcalorias.Adapter;

/**
 * Created by gustavo on 11/11/15.
 */
public class AtividadeFisica {
    public String id;
    public String nome;
    public String caloria;
    public String tempo;

    public AtividadeFisica(String id, String nome, String caloria, String tempo) {
        this.id = id;
        this.nome = nome;
        this.caloria = caloria;
        this.tempo = tempo;
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

    public String getCaloria() {
        return caloria;
    }

    public void setCaloria(String caloria) {
        this.caloria = caloria;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
}

