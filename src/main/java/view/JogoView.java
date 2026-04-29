package view;

import javax.swing.JPanel;
import java.awt.*;

public class JogoView extends JPanel {
    private Mapa mapa;
    private final int ESPACO_ADICIONAL_H = 200; // Espaço para um menu lateral, por exemplo
    private final int ESPACO_ADICIONAL_V = 50;  // Espaço para um HUD superior

    public JogoView(Mapa mapa) {
        this.mapa = mapa;
        this.setLayout(new GridBagLayout());
        this.add(mapa);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Dimension getPreferredSize() {
        // Pega o tamanho ideal do mapa
        Dimension dimMapa = mapa.getPreferredSize();

        // Retorna o tamanho do mapa + seus cálculos customizados
        return new Dimension(
                dimMapa.width + ESPACO_ADICIONAL_H,
                dimMapa.height + ESPACO_ADICIONAL_V
        );
    }
}