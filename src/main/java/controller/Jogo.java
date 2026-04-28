package controller;

import model.*;

import view.JogoView;
import view.Mapa;
import view.Janela;
import view.MensagemFimDeJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Cobrinha cobrinha;
    private Mapa mapaView;
    private Janela janelaView;
    private JogoView jogoView;
    private Timer loopJogo;

    public Jogo() {
        tabuleiro = new Tabuleiro();
        cobrinha = new Cobrinha(tabuleiro);
    }

    public void iniciar(Janela janelaPrincipal ) {
        this.mapaView = new Mapa(Carregar.getArtes(), tabuleiro.getMapa(), cobrinha);
        this.janelaView = janelaPrincipal;
        this.jogoView = new JogoView(mapaView);
        janelaView.setConteudo(jogoView);

        iniciarMovimento();
        detectarTeclado();
    }

    public void iniciarMovimento() {
        loopJogo = new Timer(150, _ -> {
            cobrinha.tentarAndar();

            if(!cobrinha.estaViva()) {
                pararJogo();
                return;
            }

            cobrinha.tentarEngolir();
            mapaView.repaint();
        });
        loopJogo.start();
    }

    private void pararJogo() {
        if(loopJogo != null) {
            loopJogo.stop();
        }

        MensagemFimDeJogo telaFim = new MensagemFimDeJogo();
        int escolha = telaFim.mostrar(cobrinha.getRazaoMorte(), janelaView);

        if (escolha == 0) {
            // Reiniciar jogo //
            cobrinha.reiniciarEstado();
            iniciarMovimento();
            tabuleiro.limparLixosMapa();
            tabuleiro.gerarLixo();
        } else if (escolha == 1) {
            // voltarMenuPrincipal();
        }
    }

    private void detectarTeclado() {
        janelaView.addKeyListener(new KeyAdapter() {
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

        janelaView.setFocusable(true);
        janelaView.requestFocusInWindow();
    }

    private void tentarMudarDirecao(Direcao novaDirecao) {
        Direcao atual = cobrinha.getDirecao();
        if (!atual.ehOposta(novaDirecao)) {
            cobrinha.setDirecao(novaDirecao);
            mapaView.repaint();
        }
    }
}
