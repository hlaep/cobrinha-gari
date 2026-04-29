package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

public class Cobrinha {
    private Direcao direcao = Direcao.LESTE;
    private Coletavel bucho = Coletavel.NENHUM;
    private int tamanho = 5; // Tamanho Inicial
    private final List<Point> corpo;
    private final Tabuleiro tabuleiro;
    private boolean viva = true;
    private String razaoMorte;

    public Cobrinha(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        corpo = new ArrayList<>();
        setCorpoInicial();
    }

    public void setCorpoInicial() {
        corpo.clear();
        corpo.add(new Point(15,15));
        corpo.add(new Point(15,16));
    }

    public List<Point> getCorpo() {
        return corpo;
    }

    public String getRazaoMorte() {
        return razaoMorte;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public boolean estaViva() {
        return viva;
    }

    public void crescer() {
        tamanho++;
    }

    public void tentarEngolir() {
        Point cabeca = corpo.getFirst();
        Espaco espaco = tabuleiro.getMapa()[cabeca.y][cabeca.x];

        Coletavel coletavelNovo = espaco.getColetavel();
        if(bucho == Coletavel.NENHUM && coletavelNovo != Coletavel.NENHUM) {
            if(coletavelNovo.ehLixo()) {
                bucho = coletavelNovo;

            }
            espaco.setColetavel(Coletavel.NENHUM);
        }
    }

    public void tentarAndar() {
        if(!viva) return;

        int novaPosicaoX = corpo.getFirst().x;
        int novaPosicaoY = corpo.getFirst().y;

        switch(direcao) {
            case NORTE -> novaPosicaoY--;
            case SUL -> novaPosicaoY++;
            case LESTE -> novaPosicaoX++;
            case OESTE -> novaPosicaoX--;
        }

        int limiteXYMapa = tabuleiro.getDIMENSAO();

        // Nova posição teleporta para o outro lado se sair do mapa //
        if(estaFora(novaPosicaoX, limiteXYMapa)) novaPosicaoX = teleportar(novaPosicaoX, limiteXYMapa);
        if(estaFora(novaPosicaoY, limiteXYMapa)) novaPosicaoY = teleportar(novaPosicaoY, limiteXYMapa);

        Point proximoPonto = new Point(novaPosicaoX, novaPosicaoY);

        // Informações necessárias para ehPontoDeEntrega() e entregarLixo() //
        Point pontoAtual = corpo.getFirst();
        Espaco espacoAtual = tabuleiro.getMapa()[pontoAtual.y][pontoAtual.x];

        if(colideComCorpo(proximoPonto) || colideComObjeto(proximoPonto)) {
            viva = false;
            razaoMorte = "colisão";
            return;
        }
        if(ehPontoDeEntrega(espacoAtual) && bucho != Coletavel.NENHUM) entregarLixo(espacoAtual);

        andar(novaPosicaoX, novaPosicaoY);
    }

    public void reiniciarEstado() {
        tamanho = 2;
        bucho = Coletavel.NENHUM;
        razaoMorte = null;
        viva = true;
        setDirecao(Direcao.LESTE);
        setCorpoInicial();
    }

    private boolean estaFora(int valor, int maximo) {
        return valor < 0 || valor >= maximo; // entre 0 e máximo //
    }

    private int teleportar(int valor, int maximo) {
        if(valor < 0) return maximo - 1;
        else return 0;
    }

    private boolean colideComCorpo(Point proximoPonto) {
        for(Point parteDoCorpo : corpo) {
            if(proximoPonto.equals(parteDoCorpo)) {
                return true;
            }
        }
        return false;
    }

    private boolean colideComObjeto(Point proximoPonto) {
        Espaco espacoAFrente = tabuleiro.getMapa()[proximoPonto.y][proximoPonto.x];
        return espacoAFrente.getTipo().ehIntransitavel();
    }

    private boolean ehPontoDeEntrega(Espaco espacoAtual) {
        return espacoAtual.getTipo() == EspacoTipo.PONTO_ENTREGA;
    }

    private void entregarLixo(Espaco lixeira) {
        boolean entregaEstaCerta = lixeira.verificarSeAceitaEntrega(bucho);

        if(entregaEstaCerta) {
        } else {
        }
        bucho = Coletavel.NENHUM;
        tabuleiro.gerarLixo();
    }

    private void andar(int novaPosicaoX, int novaPosicaoY) {
        corpo.addFirst(new Point(novaPosicaoX, novaPosicaoY));
        if(corpo.size() > tamanho) {
            corpo.removeLast();
        }
    }

}
