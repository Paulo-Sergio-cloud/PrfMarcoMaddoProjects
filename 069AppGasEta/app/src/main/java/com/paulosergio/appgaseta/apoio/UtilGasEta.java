package com.paulosergio.appgaseta.apoio;

import android.content.Context;

public class UtilGasEta {

    public static String calcularMelhorOpcao(double gasolina, double etanol) {
        /// preço da gasolina: R$ 5.12
        /// preço do etanol: R$ 3.99
        /// preço ideal = gasolina * 0.70 = R$ 3.548

        double precoIdeal = gasolina * 0.7;
        String mensagemDeRetorno;

        if (etanol<=precoIdeal) {
            mensagemDeRetorno = "Abasteça com Etanol";
        }else{
            mensagemDeRetorno = "Abasteça com Gasolina";
        }

        return mensagemDeRetorno;
    }

}
