package view;

import controller.Carregar;
import model.Coletavel;
import model.Espaco;
import model.Cobrinha;

import java.awt.*;

import javax.swing.JPanel;
import java.util.List;
import java.util.Map;

import static view.Configuracoes.ESPACO_TAMANHO;

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

        int espacoTamanho = ESPACO_TAMANHO;

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

        List<Point> corpo = cobrinha.getCorpo();
        // Pega as keys para selecionar a imagem em imagens, assim não precisa carregar imagens todos os frames //
        List<String> corpoKeys = Carregar.getKeyArteCorpoCobrinha(corpo, cobrinha.getDirecao());

        for(int i = 0; i < corpo.size(); i++) {
            Image img = imagens.get(corpoKeys.get(i));
            g.drawImage(
                    img,
                    corpo.get(i).x * espacoTamanho,
                    corpo.get(i).y * espacoTamanho,
                    espacoTamanho,
                    espacoTamanho,
                    this
            );
        }
    }
}