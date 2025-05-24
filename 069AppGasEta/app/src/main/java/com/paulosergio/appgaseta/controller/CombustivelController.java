package com.paulosergio.appgaseta.controller;

import android.content.SharedPreferences;

import com.paulosergio.appgaseta.model.Combustivel;
import com.paulosergio.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;
    SharedPreferences.Editor editorPreferences;
    public static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){

        preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);
        editorPreferences = preferences.edit();

    }

    public void salvar(Combustivel combustivel1, Combustivel combustivel2){

        /// Resolvido o problema da sobrescrição do XML

        editorPreferences.putString("combustivel1", combustivel1.getNomeDoCombustivel());
        editorPreferences.putFloat("precoDoCombustivel1", (float) combustivel1.getPrecoDoCombustivel());
        editorPreferences.putString("recomendacao", combustivel1.getRecomendacao());

        editorPreferences.putString("combustivel2", combustivel2.getNomeDoCombustivel());
        editorPreferences.putFloat("precoDoCombustivel2", (float) combustivel2.getPrecoDoCombustivel());
        editorPreferences.putString("recomendacao", combustivel2.getRecomendacao());

        editorPreferences.apply();

    }

    public void limpar(){

        editorPreferences.clear();
        editorPreferences.apply();

    }

}
