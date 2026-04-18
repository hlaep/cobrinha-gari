package model;

import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro {
    private final int DIMENSAO = 30;
    private final Espaco[][] mapa;

    public Tabuleiro() {
        mapa = new Espaco[DIMENSAO][DIMENSAO];

        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                mapa[i][j] = new Espaco("vazio");
            }
        }
        gerarLixo();
    }

    private int aleatorizar(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    private void gerarLixo() {
        int xSorteado = aleatorizar(DIMENSAO);
        int ySorteado = aleatorizar(DIMENSAO);

        String[] tiposLixo = {"orgânico", "plástico", "papel", "vidro", "metal"};
        String tipoSorteado = tiposLixo[aleatorizar(tiposLixo.length)];

        mapa[xSorteado][ySorteado].setTipo(tipoSorteado);
    }

    public Espaco[][] getMapa() {
        return mapa;
    }
}