package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Janela extends JFrame {
    private JPanel conteudo;
    public Janela(JPanel painel, Dimension tamanhoJogo) {
        super("Cobrinha Gari");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setConteudo(painel);

        this.getContentPane().setPreferredSize(tamanhoJogo);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void setConteudo( JPanel novoPainel) {
        if (conteudo != null) {
            this.remove(conteudo);
        }
        conteudo = novoPainel;
        this.getContentPane().add(conteudo);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        novoPainel.requestFocusInWindow();
    }
}