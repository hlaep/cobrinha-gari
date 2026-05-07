package view;

import controller.Carregar;
import model.Coletavel;
import model.Espaco;
import model.Cobrinha;

import java.awt.*;

import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static view.Configuracoes.ESPACO_TAMANHO;

public class Mapa extends JPanel {
    private final Espaco[][] mapa;
    private final Map<String, Image> imagens;
    private final Cobrinha cobrinha;
    private List<EfeitoVisual> efeitos = new ArrayList<>();


    public Mapa(Map<String, Image> imagens, Espaco[][] mapa, Cobrinha cobrinha) {
        this.setPreferredSize(Configuracoes.getTamanhoMapa());
        this.imagens = imagens;
        this.mapa = mapa;
        this.cobrinha = cobrinha;
    }

    public void adicionarExplosao(Point coordenada) {
        efeitos.add(new EfeitoVisual("explosão", coordenada));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o mapa //
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

        // Desenha a cobrinha //
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

        // Desenha explosão //
        long agora = System.currentTimeMillis();

        efeitos.removeIf(e -> agora - e.tempoCriacao > 250); // remove efeito antigo se tiver //
        for(EfeitoVisual efeito: efeitos) {
            Image imagemExplosao = imagens.get("explosão");
            g.drawImage(
                    imagemExplosao,
                    efeito.coordenada.x * espacoTamanho,
                    efeito.coordenada.y * espacoTamanho,
                    espacoTamanho,
                    espacoTamanho,
                    this
                    );
        }
    }
}