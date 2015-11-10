package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by gustavo on 04/11/15.
 */

public class Diario extends SugarRecord<Diario> {
    int caloriasConsumidas;
    int caloriasQueimadas;
    int caloriasRestantes;
    Date data;
    /*List<Alimentos> cafeDaManha;
    List<Alimentos> almoco;
    List<Alimentos> janta;
    List<Alimentos> lanches;*/

    public Diario(){
    }

    public Diario(int caloriasConsumidas, int caloriasQueimadas, int caloriasRestantes, Date data/*, List<Alimentos> cafeDaManha,
                    List<Alimentos> almoco, List<Alimentos> janta, List<Alimentos> lanches*/){

        this.caloriasConsumidas = caloriasConsumidas;
        this.caloriasQueimadas = caloriasQueimadas;
        this.caloriasRestantes = caloriasRestantes;
        this.data = data;
        /*this.cafeDaManha = cafeDaManha;
        this.almoco = almoco;
        this.janta = janta;
        this.lanches = lanches;*/
    }

    public int getCaloriasConsumidas() {
        return caloriasConsumidas;
    }

    public void setCaloriasConsumidas(int caloriasConsumidas) {
        this.caloriasConsumidas = caloriasConsumidas;
    }

    public int getCaloriasQueimadas() {
        return caloriasQueimadas;
    }

    public void setCaloriasQueimadas(int caloriasQueimadas) {
        this.caloriasQueimadas = caloriasQueimadas;
    }

    public int getCaloriasRestantes() {
        return caloriasRestantes;
    }

    public void setCaloriasRestantes(int caloriasRestantes) {
        this.caloriasRestantes = caloriasRestantes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    /*public List<Alimentos> getCafeDaManha() {
        return cafeDaManha;
    }

    public void setCafeDaManha(List<Alimentos> cafeDaManha) {
        this.cafeDaManha = cafeDaManha;
    }

    public List<Alimentos> getAlmoco() {
        return almoco;
    }

    public void setAlmoco(List<Alimentos> almoco) {
        this.almoco = almoco;
    }

    public List<Alimentos> getJanta() {
        return janta;
    }

    public void setJanta(List<Alimentos> janta) {
        this.janta = janta;
    }

    public List<Alimentos> getLanches() {
        return lanches;
    }

    public void setLanches(List<Alimentos> lanches) {
        this.lanches = lanches;
    }*/
}
