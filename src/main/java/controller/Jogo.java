package controller;

import model.*;

import view.JogoView;
import view.Mapa;
import view.Janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Cobrinha cobrinha;
    private Mapa mapaView;
    private Janela janela;

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.cobrinha = new Cobrinha();
    }

    public void iniciar(Janela janelaPrincipal ) {
        this.mapaView = new Mapa(Carregar.getArtes(), tabuleiro.getMapa(), cobrinha);
        this.janela = janelaPrincipal;
        janela.setConteudo(new JogoView(mapaView));

        iniciarMovimento();
        detectarTeclado();
    }

    public void iniciarMovimento() {
        Timer timer = new Timer(150, _ -> {
            cobrinha.andar(tabuleiro.getDIMENSAO());
            verificarTerreno();
            mapaView.repaint();
        });
        timer.start();
    }

    private void verificarTerreno() {
        Point cabeca = cobrinha.getCorpo().getFirst();
        Espaco espaco = tabuleiro.getMapa()[cabeca.y][cabeca.x];

        cobrinha.tentarEngolir(espaco);

    }

    private void detectarTeclado() {
        janela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                for(Direcao d: Direcao.values()) {
                    if(e.getKeyCode() == d.getKeyCode()) {
                        tentarMudarDirecao(d);
                        break;
                    }
                }
            }
        });

        janela.setFocusable(true);
        janela.requestFocusInWindow();
    }

    private void tentarMudarDirecao(Direcao novaDirecao) {
        Direcao atual = cobrinha.getDirecao();
        if (!atual.ehOposta(novaDirecao)) {
            cobrinha.setDirecao(novaDirecao);
            mapaView.repaint();
        }
    }



}
