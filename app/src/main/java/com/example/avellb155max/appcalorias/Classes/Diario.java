package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by gustavo on 04/11/15.
 */

public class Diario extends SugarRecord<Diario> {
    int caloriasConsumidas;
    int caloriasQueimadas;
    int caloriasRestantes;
    Date data;
    ItensDiario itensDiario;

    public Diario(){
    }

    public Diario(int caloriasConsumidas, int caloriasQueimadas, int caloriasRestantes, Date data, ItensDiario itensDiario) {
        this.caloriasConsumidas = caloriasConsumidas;
        this.caloriasQueimadas = caloriasQueimadas;
        this.caloriasRestantes = caloriasRestantes;
        this.data = data;
        this.itensDiario = itensDiario;
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

    public ItensDiario getItensDiario() {
        return itensDiario;
    }

    public void setItensDiario(ItensDiario itensDiario) {
        this.itensDiario = itensDiario;
    }

    /*public void AtualizarCalorias(long idDiario){
        Diario diario = Diario.findById(Diario.class,idDiario);

        if(atividadesDiarias.getTipo() == 5){
            diario.caloriasQueimadas = diario.caloriasQueimadas - diario.atividadesDiarias.getCaloria();

            diario.caloriasRestantes = diario.caloriasRestantes + diario.caloriasQueimadas;
        } else {
            diario.caloriasQueimadas = diario.caloriasQueimadas + diario.atividadesDiarias.getCaloria();

            diario.caloriasRestantes = diario.caloriasRestantes - diario.caloriasQueimadas;
        }

        diario.save();
    }*/
}
