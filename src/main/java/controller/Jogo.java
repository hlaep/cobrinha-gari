package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Cobrinha cobrinha;
    private final PV cobrinhaPV_View;
    private final Mapa mapaView;
    private Janela janelaView;
    private final JogoView jogoView;
    private Timer loopJogo;
    private final MenuPrincipal menuController;
    private Direcao direcaoUltimoPasso;

    public Jogo( MenuPrincipal menuController) {
        tabuleiro = new Tabuleiro();
        cobrinha = new Cobrinha(tabuleiro);
        this.mapaView = new Mapa(Carregar.getArtesMapa(), tabuleiro.getMapa(), cobrinha);
        this.cobrinhaPV_View = new PV(cobrinha);
        this.jogoView = new JogoView(mapaView, cobrinhaPV_View);
        this.menuController = menuController;
    }

    public void iniciar(Janela janelaPrincipal) {
        this.janelaView = janelaPrincipal;
        janelaView.setConteudo(jogoView);

        tabuleiro.gerarLixo(cobrinha.getCorpo());
        iniciarMovimento();
        detectarTeclado();
    }

    public void iniciarMovimento() {
        direcaoUltimoPasso = cobrinha.getDirecao();
        loopJogo = new Timer(140, _ -> {
            int pvAntes = cobrinha.getPV();
            cobrinha.tentarAndar();
            direcaoUltimoPasso = cobrinha.getDirecao();

            if(!cobrinha.estaViva()) {
                pararJogo();
                return;
            }

            cobrinha.tentarEngolir();
            mapaView.repaint();

            if(pvAntes > cobrinha.getPV()) {
                cobrinhaPV_View.revalidate();
                cobrinhaPV_View.repaint();
            }
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
            tabuleiro.gerarLixo(cobrinha.getCorpo());
        } else if (escolha == 1 || escolha == -1) menuController.voltarMenuPrincipal(); // volta para o menu principal //
    }

    private void detectarTeclado() {
        janelaView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                for(Direcao novaDirecao: Direcao.values()) {
                    if(e.getKeyCode() == novaDirecao.getKeyCode()) {
                        tentarMudarDirecao(novaDirecao);
                        break;
                    }
                }
            }
        });

        janelaView.setFocusable(true);
        janelaView.requestFocusInWindow();
    }

    private void tentarMudarDirecao(Direcao novaDirecao) {
        if (!direcaoUltimoPasso.ehOposta(novaDirecao)) {
            cobrinha.setDirecao(novaDirecao);
            mapaView.repaint();
        }
    }
}
