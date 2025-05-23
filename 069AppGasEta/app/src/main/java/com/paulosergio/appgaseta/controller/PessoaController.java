package com.paulosergio.appgaseta.controller;

import android.content.SharedPreferences;

import com.paulosergio.appgaseta.model.Pessoa;
import com.paulosergio.appgaseta.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaAlunos;
    public static final String NOME_PREFERENCES = "pref_lista_alunos";

    public PessoaController(MainActivity mainActivity){

        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        listaAlunos = preferences.edit();

    }

    public void salvar(Pessoa aluno){

        listaAlunos.putString("primeiroNome", aluno.getPrimeiroNome());
        listaAlunos.putString("sobreNome", aluno.getSobreNome());
        listaAlunos.putString("cursoDesejado", aluno.getCursoDesejado());
        listaAlunos.putString( "telefoneContato", aluno.getTelefoneContato());
        listaAlunos.apply();

    }

    public void limpar(Pessoa aluno){

        listaAlunos.clear();
        listaAlunos.apply();

    }

    public Pessoa buscar(Pessoa aluno){

        aluno.setPrimeiroNome(preferences.getString("primeiroNome", "NA"));
        aluno.setSobreNome(preferences.getString("sobreNome", "NA"));
        aluno.setCursoDesejado(preferences.getString("cursoDesejado", "NA"));
        aluno.setTelefoneContato(preferences.getString("telefoneContato", "NA"));

        return aluno;
    }

}
