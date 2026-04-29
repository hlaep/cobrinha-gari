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
    private final MenuPrincipal menuController;

    public Jogo( MenuPrincipal menuController) {
        tabuleiro = new Tabuleiro();
        cobrinha = new Cobrinha(tabuleiro);
        this.mapaView = new Mapa(Carregar.getArtes(), tabuleiro.getMapa(), cobrinha);
        this.jogoView = new JogoView(mapaView);
        this.menuController = menuController;
    }

    public Dimension getTamanhoJogo() {
        return jogoView.getPreferredSize();
    }

    public void iniciar(Janela janelaPrincipal) {
        this.janelaView = janelaPrincipal;
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
        } else if (escolha == 1) menuController.voltarMenuPrincipal(); // volta para o menu principal //
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
