package view;

import java.awt.Point;

public class EfeitoVisual {
    /* Se no futuro adicionarmos mais efeitos, podemos renomear esta classe para EfeitoVisual
     e por um atributo de String tipo */
    public Point coordenada;
    public long tempoCriacao;
    public String tipo;

    public EfeitoVisual(String tipo, Point coordenada) {
        this.tipo = tipo;
        this.coordenada = coordenada;
        this.tempoCriacao = System.currentTimeMillis();

    }
}
