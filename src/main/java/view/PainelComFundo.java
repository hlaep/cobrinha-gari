package view;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

public class PainelComFundo extends JPanel {
    final private Image imagemFundo;
    public PainelComFundo(Image img) {
        this.imagemFundo = img;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagemFundo == null) return;

        int painelLargura = getWidth();
        int painelAltura = getHeight();
        int imgLargura = imagemFundo.getWidth(this);
        int imgAltura = imagemFundo.getHeight(this);

        // Acha o começo da imagem central
        int desvioX = (painelLargura % imgLargura) / 2;
        int desvioY = (painelAltura % imgAltura) / 2;

        // 2. Adjust starting point to ensure the left/top edges are covered
        // We start one image-width back to fill the gap created by the offset
        for (int x = desvioX - imgLargura; x < painelLargura; x += imgLargura) {
            for (int y = desvioY - imgAltura; y < painelAltura; y += imgAltura) {
                g.drawImage(imagemFundo, x, y, this);
            }
        }
    }
}