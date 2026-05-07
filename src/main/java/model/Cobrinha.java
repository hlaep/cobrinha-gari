package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

public class Cobrinha {
    private int tamanho = 3; // Tamanho Inicial
    private Coletavel bucho = Coletavel.NENHUM;
    private final List<Point> corpo;
    private int pv = 3;
    private boolean viva = true;
    private Direcao direcao = Direcao.LESTE;
    private String razaoMorte;

    private final Tabuleiro tabuleiro;

    public Cobrinha(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        corpo = new ArrayList<>();
        setCorpoInicial();
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

    public int getPV() {
        return pv;
    }

    public boolean estaViva() {
        return viva;
    }

    public void setCorpoInicial() {
        corpo.clear();
        corpo.add(new Point(15,15));
        corpo.add(new Point(15,16));
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public void reiniciarEstado() {
        tamanho = 3;
        bucho = Coletavel.NENHUM;
        razaoMorte = null;
        viva = true;
        pv = 3;
        setDirecao(Direcao.LESTE);
        setCorpoInicial();
    }

    public void morrer(String razaoMorte) {
        viva = false;
        this.razaoMorte = razaoMorte;
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

        // Informações necessárias para ehPontoDeEntrega(), entregarLixo(), colideComObjeto() e tentarEngolir() //
        Point pontoAtual = corpo.getFirst();
        Espaco espacoAtual = tabuleiro.getMapa()[pontoAtual.y][pontoAtual.x];
        Espaco espacoAFrente = tabuleiro.getMapa()[proximoPonto.y][proximoPonto.x];

        tentarEngolir(espacoAtual);

        if(colideComCorpo(proximoPonto)) {
            morrer("tentar engolir a si mesma");
            return;
        }
        if(colideComObjeto(espacoAFrente)) {
            String objetoAFrente;
            if(espacoAFrente.getTipo().ehLixeira()) objetoAFrente = "lixeira";
            else objetoAFrente = espacoAFrente.getTipo().toString().toLowerCase();
            morrer("tentar engolir " + objetoAFrente);
        }

        if(ehPontoDeEntrega(espacoAtual) && bucho != Coletavel.NENHUM) entregarLixo(espacoAtual);

        andar(novaPosicaoX, novaPosicaoY);
    }

    private boolean estaFora(int valor, int maximo) {
        return valor < 0 || valor >= maximo; // entre 0 e máximo //
    }

    private int teleportar(int valor, int maximo) {
        if(valor < 0) return maximo - 1;
        else return 0;
    }

    public void tentarEngolir(Espaco espacoAtual) {
        Coletavel coletavelNovo = espacoAtual.getColetavel();

        // Engolir lixo //
        if(bucho == Coletavel.NENHUM && coletavelNovo.ehLixo()) {
            bucho = coletavelNovo;
            espacoAtual.setColetavel(Coletavel.NENHUM);
        }
        // Aplicar efeito de coletável especial (não precisa engolir) //
        if(coletavelNovo == Coletavel.MACA) {
            tamanho++;
            espacoAtual.setColetavel(Coletavel.NENHUM);
        }
        if(coletavelNovo == Coletavel.BOMBA) {
            pv--;
            if(pv <= 0) {
                viva = false;
                morrer("não resistir a explosão");
            }
            espacoAtual.setColetavel(Coletavel.NENHUM);
        }
    }

    private boolean colideComCorpo(Point proximoPonto) {
        for(Point parteDoCorpo : corpo) {
            if(proximoPonto.equals(parteDoCorpo)) {
                return true;
            }
        }
        return false;
    }

    private boolean colideComObjeto(Espaco espacoAFrente) {
        return espacoAFrente.getTipo().ehIntransitavel();
    }

    private boolean ehPontoDeEntrega(Espaco espacoAtual) {
        return espacoAtual.getTipo() == EspacoTipo.PONTO_ENTREGA;
    }

    private void entregarLixo(Espaco lixeira) {
        boolean entregaEstaCerta = lixeira.verificarSeAceitaEntrega(bucho);

        if(entregaEstaCerta) tabuleiro.gerarColetavel(Coletavel.MACA, corpo); // Recompensa //
        else tabuleiro.gerarColetavel(Coletavel.BOMBA, corpo); // Punição //
        bucho = Coletavel.NENHUM;
        tabuleiro.gerarLixo(corpo);
    }

    private void andar(int novaPosicaoX, int novaPosicaoY) {
        corpo.addFirst(new Point(novaPosicaoX, novaPosicaoY));
        while(corpo.size() > tamanho) {
            if(corpo.size() <= 2) return;
            corpo.removeLast();
        }
    }

}