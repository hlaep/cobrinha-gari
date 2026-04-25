package controller;

import view.Mapa;
import view.Janela;

import model.Tabuleiro;
import model.Cobrinha;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Cobrinha cobrinha;
    private Mapa mapa;
    private Janela janela;


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
            mapa.repaint();
        }
    }

    public void iniciarMovimento() {
        Timer timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cobrinha.andar();
                mapa.repaint();
            }
        });
        timer.start();
    }

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.cobrinha = new Cobrinha();
    }

    public void iniciar(Janela janelaPrincipal ) {
        this.mapa = new Mapa(Carregar.getArtes(), tabuleiro.getMapa(), cobrinha);
        this.janela = janelaPrincipal;
        janela.setConteudo(new view.Jogo(mapa));

        iniciarMovimento();
        detectarTeclado();
    }

}
