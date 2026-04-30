package view;

import controller.Carregar;
import model.Cobrinha;

import java.awt.*;
import javax.swing.JPanel;

public class PV extends JPanel{
    private final Cobrinha cobrinha;
    private final Image coracaoImg = Carregar.getImagem("/coracao.png");

    public PV(Cobrinha cobrinha) {
        this.cobrinha = cobrinha;
        setPreferredSize(new Dimension(cobrinha.getPV() * 35, Configuracoes.alturaPVDisplay));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < cobrinha.getPV(); i++) {
            // O 32, 32 final define o tamanho da renderização
            g.drawImage(coracaoImg, i * 32, 0, 32, 32, null);
        }
    }
}
