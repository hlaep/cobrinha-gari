package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame {
    private JPanel conteudo;
    public Janela(JPanel painel) {
        super("Cobrinha Gari");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setConteudo(painel);
    }

    public void setConteudo( JPanel painel) {
        if (this.conteudo != null) {
            this.remove(this.conteudo);
        }
        this.conteudo = painel;
        this.add(this.conteudo);

        this.revalidate();
        this.repaint();
    }
}