package com.example.avellb155max.appcalorias.Adapter;

/**
 * Created by gustavo on 11/11/15.
 */
public class AtividadeFisica {
    public String id;
    public String nomeDoExercicio;
    public String caloriaGasta;
    public String tempoDeExercicio;

    public AtividadeFisica(String id, String nomeDoExercicio, String caloriaGasta, String tempoDeExercicio) {
        this.id = id;
        this.nomeDoExercicio = nomeDoExercicio;
        this.caloriaGasta = caloriaGasta;
        this.tempoDeExercicio = tempoDeExercicio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeDoExercicio() {
        return nomeDoExercicio;
    }

    public void setNomeDoExercicio(String nomeDoExercicio) {
        this.nomeDoExercicio = nomeDoExercicio;
    }

    public String getCaloriaGasta() {
        return caloriaGasta;
    }

    public void setCaloriaGasta(String caloriaGasta) {
        this.caloriaGasta = caloriaGasta;
    }

    public String getTempoDeExercicio() {
        return tempoDeExercicio;
    }

    public void setTempoDeExercicio(String tempoDeExercicio) {
        this.tempoDeExercicio = tempoDeExercicio;
    }
}

