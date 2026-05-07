package model;

public class Pontuacao {
    private int pontuacao = 0;

    public void ganharPontos(int quantidade) {
        pontuacao += quantidade;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}
