package model;

import java.awt.*;
import java.util.Map;

public enum Direcao {
    NORTE(38, "norte"),
    SUL(40, "sul"),
    LESTE(39, "leste"),
    OESTE(37, "oeste");

    private final int keyCode;
    private final String nome;

    Direcao(int keyCode, String nome) {
        this.keyCode = keyCode; this.nome = nome;
    }

    public int getKeyCode() { return keyCode; }

    public String getNome() {return nome; }

    public Image getArteCabeca(Map<String, Image> imagens) {
        return imagens.get("cabeça " + this.getNome());
    }

    public Image getArteCorpo(Map<String, Image> imagens) {
        if(this == NORTE || this == SUL) {
            return imagens.get("corpo vertical");
        } else return imagens.get("corpo horizontal");
    }

    public Image getArteRabo(Map<String, Image> imagens) {
        return imagens.get("rabo " + this.getNome());
    }

    /* Método para evitar que a cobra volte sobre o próprio corpo */
    public boolean ehOposta(Direcao outra) {
        return (this == NORTE && outra == SUL) ||
                (this == SUL && outra == NORTE) ||
                (this == LESTE && outra == OESTE) ||
                (this == OESTE && outra == LESTE);
    }
}