package view;

import model.Espaco;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Map;

public class Mapa extends JPanel {
    private final Espaco[][] mapa;
    private final int ESPACO_TAMANHO = 20;
    private final Map<String, Image> imagens;

    public Mapa(Map<String, Image> imagens, Espaco[][] mapa) {
        this.imagens = imagens;
        this.mapa = mapa;

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
    }
}