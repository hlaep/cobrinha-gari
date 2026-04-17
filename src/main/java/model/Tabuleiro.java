package model;

public class Tabuleiro {
    private int DIMENSAO = 30;
    private Espaco[][] mapa;

    public Tabuleiro() {
        mapa = new Espaco[DIMENSAO][DIMENSAO];

        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                mapa[i][j] = new Espaco("vazio");
            }
        }
    }

    public Espaco[][] getMapa() {
        return mapa;
    }
}