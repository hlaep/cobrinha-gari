package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;


public class Cobrinha {
    private Direcao direcao = Direcao.LESTE;
    private Coletavel bucho = Coletavel.NENHUM;
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

    public void tentarEngolir(Espaco espaco) {
        Coletavel coletavelNovo = espaco.getColetavel();
        if(bucho == Coletavel.NENHUM && coletavelNovo != Coletavel.NENHUM) {
            if(coletavelNovo.ehLixo()) {
                bucho = coletavelNovo;

            }
            espaco.setColetavel(Coletavel.NENHUM);
        }
    }

    public void andar(int limiteXYMapa) {
        int novaPosicaoX = corpo.getFirst().x;
        int novaPosicaoY = corpo.getFirst().y;

        switch(direcao) {
            case NORTE: novaPosicaoY--; break;
            case SUL:   novaPosicaoY++; break;
            case LESTE: novaPosicaoX++; break;
            case OESTE: novaPosicaoX--; break;
        }

        if(estaFora(novaPosicaoX, limiteXYMapa)) {
            novaPosicaoX = teleportar(novaPosicaoX, limiteXYMapa);
        }
        if(estaFora(novaPosicaoY, limiteXYMapa)) {
            novaPosicaoY = teleportar(novaPosicaoY, limiteXYMapa);
        }

        corpo.addFirst(new Point(novaPosicaoX, novaPosicaoY));
        if(corpo.size() > tamanho) {
            corpo.removeLast();
        }
    }

    private boolean estaFora(int valor, int maximo) {
        return valor < 0 || valor >= maximo; // entre 0 e máximo //
    }

    private int teleportar(int valor, int maximo) {
        if(valor < 0) return maximo - 1;
        else return 0;
    }
}
