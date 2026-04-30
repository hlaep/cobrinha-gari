package view;

import javax.swing.JPanel;
import java.awt.*;

public class JogoView extends JPanel {
    private final Mapa mapa;

    public JogoView(Mapa mapa) {
        this.mapa = mapa;
        this.setLayout(new GridBagLayout());
        this.add(mapa);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension dimMapa = mapa.getPreferredSize();

        // Retorna o tamanho do mapa + seus cálculos customizados quando precisar //
        return new Dimension(
                dimMapa.width,
                dimMapa.height
        );
    }
}