package view;

import controller.Direcao;
import model.Espaco;
import model.Cobrinha;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;
import java.util.Map;
import java.util.List;

public class Mapa extends JPanel {
    private final Espaco[][] mapa;
    private final int ESPACO_TAMANHO = 32;
    private final Map<String, Image> imagens;
    private Cobrinha cobrinha;


    public Mapa(Map<String, Image> imagens, Espaco[][] mapa, Cobrinha cobrinha) {
        this.imagens = imagens;
        this.mapa = mapa;
        this.cobrinha = cobrinha;

        int larguraTotal = mapa[0].length * ESPACO_TAMANHO;
        int alturaTotal = mapa.length * ESPACO_TAMANHO;

        this.setPreferredSize(new Dimension(larguraTotal, alturaTotal));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < mapa.length; i++) {
            for(int j = 0; j < mapa[i].length; j++) {
                String tipo = mapa[i][j].getTipo();
                Image img = imagens.get(tipo);

                if(img != null) {
                    // Parâmetros: g.drawImage(imagem, x, y, largura, altura, observer)
                    g.drawImage(
                            img,
                            j * ESPACO_TAMANHO,
                            i * ESPACO_TAMANHO,
                            ESPACO_TAMANHO,
                            ESPACO_TAMANHO,
                            this
                    );

                }
            }
        }

        Direcao direcaoAtual = cobrinha.getDirecao();
        List<Point> corpo = cobrinha.getCorpo();

        for(int i = 0; i < corpo.size(); i++) {
            Image img;
            Point ponto = corpo.get(i);
            if(i == 0) img = direcaoAtual.getArteCabeca(imagens);
             else if (i == corpo.size() - 1) img = direcaoAtual.getArteRabo(imagens);
             else img = direcaoAtual.getArteCorpo(imagens);

             g.drawImage(img, ponto.x * ESPACO_TAMANHO,
                     ponto.y * ESPACO_TAMANHO,
                     ESPACO_TAMANHO,
                     ESPACO_TAMANHO,
                     this
             );
        }
    }
}