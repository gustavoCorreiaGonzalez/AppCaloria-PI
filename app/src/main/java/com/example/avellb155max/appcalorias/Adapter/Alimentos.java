package com.example.avellb155max.appcalorias.Adapter;

/**
 * Created by gustavo on 10/11/15.
 */
public class Alimentos {
    public String idCategoria;
    public String idSubCategoria;
    public String id;
    public String nome;
    public String caloria;

    public Alimentos(String idCategoria, String idSubCategoria, String id, String nome, String caloria) {
        this.idCategoria = idCategoria;
        this.idSubCategoria = idSubCategoria;
        this.id = id;
        this.nome = nome;
        this.caloria = caloria;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(String idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
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
}
