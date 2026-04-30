package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Janela extends JFrame {
    public Janela(JPanel painelInicial) {
        super("Cobrinha Gari");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setConteudo(painelInicial);
    }

    public void setConteudo( JPanel novoPainel) {
        this.setContentPane(novoPainel);
        this.revalidate();
        this.repaint();
        this.pack();
        this.setLocationRelativeTo(null); // Centraliza //
        novoPainel.requestFocusInWindow();
    }
}