package com.example.avellb155max.appcalorias.Classes;

import com.orm.SugarRecord;

/**
 * Created by gustavo on 26/11/15.
 */
public class ItensDiario extends SugarRecord<Diario> {
    Diario diario;
    AtividadesDiarias atividadesDiarias;

    public ItensDiario(){
    }

    public ItensDiario(Diario diario, AtividadesDiarias atividadesDiarias) {
        this.diario = diario;
        this.atividadesDiarias = atividadesDiarias;
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
}
