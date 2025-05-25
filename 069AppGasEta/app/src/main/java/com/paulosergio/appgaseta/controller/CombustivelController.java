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

    public void salvar(Combustivel combustivel){

        /// Resolvido o problema da sobrescrição do XML

        editorPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        editorPreferences.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        editorPreferences.putString("recomendacao", combustivel.getRecomendacao());

        editorPreferences.apply();

    }

    public void limpar(){

        editorPreferences.clear();
        editorPreferences.apply();

    }

}
