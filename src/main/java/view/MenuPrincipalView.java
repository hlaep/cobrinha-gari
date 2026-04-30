package view;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Image;

public class MenuPrincipalView extends PainelComFundo {
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

        JButton[] botoes = getBotoes();

        for(JButton b : botoes) {
            b.setMaximumSize(new Dimension(150, 40));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(b);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private JButton[] getBotoes() {
        JButton jogar = new JButton("jogar");
        JButton classificacao = new JButton("Classificação");
        JButton configuracoes = new JButton("Configurações");

        jogar.addActionListener(_ -> { if(listener != null) { listener.onJogar(); }});
        classificacao.addActionListener(_ -> { if(listener != null) { listener.onClassificacao(); }});
        configuracoes.addActionListener(_ -> { if(listener != null) { listener.onConfiguracoes(); }});

        return new JButton[] { jogar, classificacao, configuracoes };
    }

    public MenuPrincipalView(Image img) {
        super(img);
        this.setPreferredSize(Configuracoes.getTamanhoJanela());
        this.criarBotoes();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}