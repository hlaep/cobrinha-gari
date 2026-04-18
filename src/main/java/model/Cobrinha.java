package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;


public class Cobrinha {
    private String direcao = "leste";
    private String bucho = "vazio";
    private int tamanho = 2; // Tamanho Inicial
    private List<Point> corpo;

    public Cobrinha() {
        corpo = new ArrayList<>();
        corpo.add(new Point(15,15));
        corpo.add(new Point(15,16));
    }

    public List<Point> getCorpo() {
        return corpo;
    }

    public void setTamanho(int n) {
        tamanho = n;
    }

    public void crescer() {
        tamanho++;
    }

    public void engolir(String lixo) {
        bucho = lixo;
    }

    public void andar() {
        int novaPosicaoX = corpo.getFirst().x;
        int novaPosicaoY = corpo.getFirst().y;

        switch(direcao) {
            case "norte" -> novaPosicaoY--;
            case "sul"   -> novaPosicaoY++;
            case "leste" -> novaPosicaoX++;
            case "oeste" -> novaPosicaoX--;
        }

        corpo.addFirst(new Point(novaPosicaoX, novaPosicaoY));

        if(corpo.size() > tamanho) {
            corpo.removeLast();
        }
    }

}
