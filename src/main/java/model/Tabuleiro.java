package model;

import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro {
    private final int DIMENSAO = 30;
    private final Espaco[][] mapa;

    public Tabuleiro() {
        mapa = new Espaco[DIMENSAO][DIMENSAO];

        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                mapa[i][j] = new Espaco(Item.NENHUM);
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

        Item[] lixos = Item.getLixos();
        Item lixoSorteado = lixos[aleatorizar(lixos.length)];

        mapa[ySorteado][xSorteado].setTipo(lixoSorteado);
    }

    public Espaco[][] getMapa() {
        // A primeira é o y e a segunda é o x mapa[y][x] //
        return mapa;
    }

    public int getDIMENSAO() {
        return DIMENSAO;
    }
}