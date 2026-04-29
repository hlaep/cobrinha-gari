package view;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Image;

public class MenuPrincipalView extends PainelComFundo {
    // Botões //
    public interface MenuListener {
        void onJogar();
        void onClassificacao();
        void onConfiguracoes();
    }

    private MenuListener listener;

    public void setListener(MenuListener listener) {
        this.listener = listener;
    }

    private void criarBotoes() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());

        JButton jogar = new JButton("jogar");
        JButton classificacao = new JButton("Classificação");
        JButton configuracoes = new JButton("Configurações");

        jogar.addActionListener(e -> { if(listener != null) { listener.onJogar(); }});
        classificacao.addActionListener(e -> { if(listener != null) { listener.onClassificacao(); }});
        configuracoes.addActionListener(e -> { if(listener != null) { listener.onConfiguracoes(); }});

        JButton[] botoes = { jogar, classificacao, configuracoes };

        for(JButton b : botoes) {
            b.setMaximumSize(new Dimension(150, 40));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(b);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    public MenuPrincipalView(Image img) {
        super(img);
        this.criarBotoes();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}