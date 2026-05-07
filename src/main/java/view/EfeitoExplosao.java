package view;

import java.awt.Point;

public class EfeitoExplosao {
    /* Se no futuro adicionarmos mais efeitos, podemos renomear esta classe para EfeitoVisual
     e por um atributo de String tipo */
    public Point coordenada;
    public long tempoCriacao;

    public EfeitoExplosao(Point coordenada) {
        this.coordenada = coordenada;
        this.tempoCriacao = System.currentTimeMillis();

    }
}
