package view;

import model.Coletavel;
import model.Espaco;
import model.Cobrinha;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;
import java.util.Map;

public class Mapa extends JPanel {
    private final Espaco[][] mapa;
    private final Map<String, Image> imagens;
    private final Cobrinha cobrinha;


    public Mapa(Map<String, Image> imagens, Espaco[][] mapa, Cobrinha cobrinha) {
        this.setPreferredSize(Configuracoes.getTamanhoMapa());
        this.imagens = imagens;
        this.mapa = mapa;
        this.cobrinha = cobrinha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int espacoTamanho = Configuracoes.ESPACO_TAMANHO;

        for(int i = 0; i < mapa.length; i++) {
            for(int j = 0; j < mapa[i].length; j++) {

                Espaco espaco = mapa[i][j];
                Image img;
                if(espaco.getColetavel() == Coletavel.NENHUM) {
                    // Renderiza com base na arte do espaco //
                    String arteEspaco = espaco.getTipo().getChaveString();
                    img = imagens.get(arteEspaco);
                } else {
                    // Renderiza com base na arte do coletável (que contém a arte do espaco vazio de fundo) //
                    String arteColetavel = espaco.getColetavel().getChaveString();
                    img = imagens.get(arteColetavel);
                }

                if(img != null) {
                    // Parâmetros: g.drawImage(imagem, x, y, largura, altura, observer)
                    g.drawImage(
                            img,
                            j * espacoTamanho,
                            i * espacoTamanho,
                            espacoTamanho,
                            espacoTamanho,
                            this
                    );

                }
            }
        }

        g.setColor(java.awt.Color.GREEN);
        for(Point p: cobrinha.getCorpo()) {
            g.fillRect(
                    p.x * espacoTamanho,
                    p.y * espacoTamanho,
                    espacoTamanho,
                    espacoTamanho
            );
        }

       /* Direcao direcaoAtual = cobrinha.getDirecao();
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
        }*/
    }
}