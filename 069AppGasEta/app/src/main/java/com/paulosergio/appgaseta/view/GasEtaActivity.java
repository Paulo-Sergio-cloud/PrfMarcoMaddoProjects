package com.paulosergio.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paulosergio.appgaseta.R;
import com.paulosergio.appgaseta.apoio.UtilGasEta;
import com.paulosergio.appgaseta.controller.CombustivelController;
import com.paulosergio.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {

    CombustivelController controller;

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEtaActivity.this);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOk = true;

                if(TextUtils.isEmpty(editGasolina.getText())) {

                    editGasolina.setError("* obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;

                }

                if(TextUtils.isEmpty(editEtanol.getText())) {

                    editEtanol.setError("* obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;

                }

                if(isDadosOk) {

                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);

                }else {
                    Toast.makeText(GasEtaActivity.this,
                            "Digite os dados obrigatórios",
                            Toast.LENGTH_LONG).show();

                    btnSalvar.setEnabled(false);
                }

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editGasolina.setText("");
                editEtanol.setText("");
                txtResultado.setText(R.string.resultado);

                controller.limpar();

                btnSalvar.setEnabled(false);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: EditText inpuType

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(recomendacao);

                /// O segundo vai sobrescrever o primeiro
                /// * poderia solucionar utilizando Array *
                //controller.salvar(combustivelGasolina);
                //controller.salvar(combustivelEtanol);

                /// Resolvido o problema da sobrescrição do XML
                controller.salvar(combustivelGasolina, combustivelEtanol);

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Testes
        /*Toast.makeText(GasEtaActivity.this,
                UtilGasEta.calcularMelhorOpcao(5.12, 3.49),
                Toast.LENGTH_LONG).show(); */

    }
}
