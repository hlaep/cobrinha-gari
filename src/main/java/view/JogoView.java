package view;

import javax.swing.JPanel;
import java.awt.*;

public class JogoView extends JPanel {
    private final JPanel mapa, pvDisplay;

    public JogoView(Mapa mapa, PV pvDisplay) {
        this.setPreferredSize(Configuracoes.getTamanhoJanela());
        this.pvDisplay = pvDisplay;
        this.mapa = mapa;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações comuns (faz os componentes ocuparem o espaço)
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; // Ambos ficam na coluna 0

        // PV Display na linha 0 (Topo)
        gbc.gridy = 0;
        gbc.weighty = 0.1; // Ocupa menos espaço vertical
        this.add(pvDisplay, gbc);

        // Mapa na linha 1 (Abaixo)
        gbc.gridy = 1;
        gbc.weighty = 0.9; // Ocupa espaço vertical
        this.add(mapa, gbc);

        this.setBackground(Color.LIGHT_GRAY);
    }
}