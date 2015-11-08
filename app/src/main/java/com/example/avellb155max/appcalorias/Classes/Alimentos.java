package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

/**
 * Created by Gustavo Correia Gonzalez on 21/10/2015.
 */
public class Alimentos extends SugarRecord<Alimentos> {
    String nome;
    String caloria;

    public Alimentos(){
    }

    public Alimentos(String nome, String caloria){
        this.nome = nome;
        this.caloria = caloria;
    }
}