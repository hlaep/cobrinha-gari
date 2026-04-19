package controller;

import view.Mapa;
import view.Janela;

import model.Tabuleiro;
import model.Cobrinha;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Cobrinha cobrinha;
    private Mapa mapa;
    private Janela janela;

    private Map<String, Image> carregarRecursosMapa() {
        return Map.of(
                "vazio", Carregar.getImagem("/terreno-vazio.png"),
                "vidro", Carregar.getImagem("/lixo-vidro.png"),
                "plástico", Carregar.getImagem("/lixo-plastico.png"),
                "papel", Carregar.getImagem("/lixo-papel.png"),
                "metal", Carregar.getImagem("/lixo-metal.png"),
                "orgânico", Carregar.getImagem("/lixo-organico.png"),
                "maçã", Carregar.getImagem("/espaco-maca.png")
        );
    }

    private void detectarTeclado() {
        janela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                IO.println(e.getKeyCode());
                switch(e.getKeyCode()) {
                    case 38:
                        if(verificarSeDirecaoNaoOposta("norte")) {
                            cobrinha.setDirecao("norte");
                        }
                        break;

                    case 40:
                        if(verificarSeDirecaoNaoOposta("sul")) {
                            cobrinha.setDirecao("sul");
                        }
                        break;
                    case 39:
                        if(verificarSeDirecaoNaoOposta("leste")) {
                            cobrinha.setDirecao("leste");
                        }
                        break;
                    case 37:
                        if(verificarSeDirecaoNaoOposta("oeste")) {
                            cobrinha.setDirecao("oeste");
                        }
                        break;
                }
            }
        });

        janela.setFocusable(true);
        janela.requestFocusInWindow();
    }

    private boolean verificarSeDirecaoNaoOposta(String direcao) {
        String atual = cobrinha.getDirecao();
        return (!direcao.equals("norte") || !atual.equals("sul")) &&
                (!direcao.equals("sul") || !atual.equals("norte")) &&
                (!direcao.equals("leste") || !atual.equals("oeste")) &&
                (!direcao.equals("oeste") || !atual.equals("leste"));
    }

    public void iniciarMovimento() {
        Timer timer = new Timer(100, new ActionListener() {
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
        this.mapa = new Mapa(carregarRecursosMapa(), tabuleiro.getMapa(), cobrinha.getCorpo());
        this.janela = janelaPrincipal;
        janela.setConteudo(new view.Jogo(mapa));

        iniciarMovimento();
        detectarTeclado();
    }

}
