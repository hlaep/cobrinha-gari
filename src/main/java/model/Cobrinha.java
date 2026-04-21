package model;

import controller.Direcao;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;


public class Cobrinha {
    private Direcao direcao = Direcao.LESTE;
    private String bucho = "vazio";
    private int tamanho = 20; // Tamanho Inicial
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

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
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
            case NORTE -> novaPosicaoY--;
            case SUL   -> novaPosicaoY++;
            case LESTE -> novaPosicaoX++;
            case OESTE -> novaPosicaoX--;
        }

        corpo.addFirst(new Point(novaPosicaoX, novaPosicaoY));

        if(corpo.size() > tamanho) {
            corpo.removeLast();
        }
    }

}
