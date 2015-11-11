package com.example.avellb155max.appcalorias.Model;

import com.orm.SugarRecord;

/**
 * Created by gustavo on 11/11/15.
 */
public class AtividadesDiarias extends SugarRecord<AtividadesDiarias> {
    String nome;
    int caloria;
    int tipo;

    public AtividadesDiarias(){

    }

    public AtividadesDiarias(String nome, int caloria, int tipo) {
        this.nome = nome;
        this.caloria = caloria;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCaloria() {
        return caloria;
    }

    public void setCaloria(int caloria) {
        this.caloria = caloria;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
