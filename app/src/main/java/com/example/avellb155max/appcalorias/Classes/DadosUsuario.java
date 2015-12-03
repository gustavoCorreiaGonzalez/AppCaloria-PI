package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

/**
 * Created by gustavo on 24/10/15.
 */
public class DadosUsuario extends SugarRecord<DadosUsuario> {
    double altura;
    double peso;

    public DadosUsuario(){
    }

    public DadosUsuario(double altura, double peso) {
        this.altura = altura;
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return this.altura;
    }

    public double getPeso() {
        return this.peso;
    }
}
