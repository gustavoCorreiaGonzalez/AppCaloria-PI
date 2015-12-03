package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

/**
 * Created by gustavo on 26/11/15.
 */
public class ItensDiario extends SugarRecord<Diario> {
    Diario diario;
    AtividadesDiarias atividadesDiarias;
    int tipo;

    public ItensDiario(){
    }

    public ItensDiario(Diario diario, AtividadesDiarias atividadesDiarias, int tipo) {
        this.diario = diario;
        this.atividadesDiarias = atividadesDiarias;
        this.tipo = tipo;
    }

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public AtividadesDiarias getAtividadesDiarias() {
        return atividadesDiarias;
    }

    public void setAtividadesDiarias(AtividadesDiarias atividadesDiarias) {
        this.atividadesDiarias = atividadesDiarias;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
