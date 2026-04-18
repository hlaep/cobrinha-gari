package controller;

import view.Mapa;
import view.Janela;

import model.Tabuleiro;
import model.Cobrinha;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Cobrinha cobrinha;
    private Mapa mapa;

    private Map<String, Image> carregarRecursosMapa() {
        return Map.of(
                "vazio", Carregar.getImagem("/terreno-vazio.png"),
                "vidro", Carregar.getImagem("/lixo-vidro.png"),
                "plástico", Carregar.getImagem("/lixo-plastico.png"),
                "papel", Carregar.getImagem("/lixo-papel.png"),
                "metal", Carregar.getImagem("/lixo-metal.png"),
                "orgânico", Carregar.getImagem("/lixo-organico.png")
        );
    }

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.cobrinha = new Cobrinha();
    }

    public void iniciar(Janela janelaPrincipal ) {
        this.mapa = new Mapa(carregarRecursosMapa(), tabuleiro.getMapa(), cobrinha.getCorpo());
        janelaPrincipal.setConteudo(new view.Jogo(mapa));

        iniciarMovimento();
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

}
