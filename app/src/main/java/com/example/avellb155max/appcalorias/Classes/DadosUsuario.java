package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

/**
 * Created by gustavo on 24/10/15.
 */
public class DadosUsuario extends SugarRecord<DadosUsuario> {
    String altura;
    String peso;

    public DadosUsuario(){
    }

    public DadosUsuario(String altura, String peso){
        this.altura = altura;
        this.peso = peso;
    }
}
