package view;

import model.Espaco;

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
    private List<Point> corpoCobrinha;

    public Mapa(Map<String, Image> imagens, Espaco[][] mapa, List<Point> corpoCobrinha) {
        this.imagens = imagens;
        this.mapa = mapa;
        this.corpoCobrinha = corpoCobrinha;

        int larguraTotal = 30 * ESPACO_TAMANHO;
        int alturaTotal = 30 * ESPACO_TAMANHO;

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

        g.setColor(java.awt.Color.GREEN);
        for(Point p: corpoCobrinha) {
            g.fillRect(
                    p.x * ESPACO_TAMANHO,
                    p.y * ESPACO_TAMANHO,
                    ESPACO_TAMANHO,
                    ESPACO_TAMANHO
            );
        }

    }
}