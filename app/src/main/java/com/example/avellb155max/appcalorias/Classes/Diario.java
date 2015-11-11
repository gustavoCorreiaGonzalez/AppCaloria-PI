package com.example.avellb155max.appcalorias.Classes;

import com.example.avellb155max.appcalorias.Model.AtividadesDiarias;
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
    AtividadesDiarias atividadesDiarias;

    public Diario(){
    }

    public Diario(int caloriasConsumidas, int caloriasQueimadas, int caloriasRestantes, Date data, AtividadesDiarias atividadesDiarias) {
        this.caloriasConsumidas = caloriasConsumidas;
        this.caloriasQueimadas = caloriasQueimadas;
        this.caloriasRestantes = caloriasRestantes;
        this.data = data;
        this.atividadesDiarias = atividadesDiarias;
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

    public AtividadesDiarias getAtividadesDiarias() {
        return atividadesDiarias;
    }

    public void setAtividadesDiarias(AtividadesDiarias atividadesDiarias) {
        this.atividadesDiarias = atividadesDiarias;
    }

    public void AtualizarCalorias(long id){
        Diario diario = Diario.findById(Diario.class,id);

        if(diario.atividadesDiarias.getTipo() == 5){
            diario.caloriasQueimadas = diario.caloriasQueimadas - diario.atividadesDiarias.getCaloria();

            diario.caloriasRestantes = diario.caloriasRestantes + diario.caloriasQueimadas;
        } else {
            diario.caloriasQueimadas = diario.caloriasQueimadas + diario.atividadesDiarias.getCaloria();

            diario.caloriasRestantes = diario.caloriasRestantes - diario.caloriasQueimadas;
        }

        diario.save();
    }
}
