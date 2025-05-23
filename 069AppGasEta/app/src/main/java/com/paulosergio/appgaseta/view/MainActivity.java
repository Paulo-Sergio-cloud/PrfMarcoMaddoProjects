package com.paulosergio.appgaseta.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.paulosergio.appgaseta.R;
import com.paulosergio.appgaseta.controller.CursoController;
import com.paulosergio.appgaseta.controller.PessoaController;
import com.paulosergio.appgaseta.model.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    PessoaController controller;
    CursoController cursoController;

    Pessoa pessoa;
    List<String> nomesDosCursos;

    private EditText editPrimeiroNome;
    private EditText editSobreNome;
    private EditText editCursoDesejado;
    private EditText editTelefoneContato;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new PessoaController(MainActivity.this);
        controller.toString();

        pessoa = new Pessoa();

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        editCursoDesejado = findViewById(R.id.editCursoDesejado);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        Button btnSalvar = findViewById(R.id.btnCalcular);
        Button btnLimpar = findViewById(R.id.btnLimpar);
        Button btnFinalizar = findViewById(R.id.btnFinalizar);

        /// Adapter
        /// Layout
        /// Injetar o Adapter no Spinner - A lista será gerada

        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSobreNome.getText().toString());
                pessoa.setCursoDesejado(editCursoDesejado.getText().toString());
                pessoa.setTelefoneContato(editTelefoneContato.getText().toString());

                controller.salvar(pessoa);

                Toast.makeText(MainActivity.this, "Salvo com sucesso" + pessoa.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controller.limpar(pessoa);

                editPrimeiroNome.setText("");
                editSobreNome.setText("");
                editCursoDesejado.setText("");
                editTelefoneContato.setText("");

                pessoa.setPrimeiroNome("");
                pessoa.setSobreNome("");
                pessoa.setCursoDesejado("");
                pessoa.setTelefoneContato("");

                Toast.makeText(MainActivity.this, "Limpo com sucesso" + pessoa.toString(), Toast.LENGTH_LONG).show();

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controller.buscar(pessoa);

                editPrimeiroNome.setText(pessoa.getPrimeiroNome());
                editSobreNome.setText(pessoa.getSobreNome());
                editCursoDesejado.setText(pessoa.getCursoDesejado());
                editTelefoneContato.setText(pessoa.getTelefoneContato());

                Toast.makeText(MainActivity.this, "Volte sempre!" + pessoa.toString(), Toast.LENGTH_LONG).show();
                //finish();
            }
        });
    }



}