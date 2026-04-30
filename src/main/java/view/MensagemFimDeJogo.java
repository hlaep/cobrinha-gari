package view;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class MensagemFimDeJogo {
    private final String[] opcoes = {"Tentar de novo", "Voltar para o menu"};

    public int mostrar(String razaoMorte, JFrame janelaMae) {
        return JOptionPane.showOptionDialog(
                janelaMae,
                "A cobrinha morreu por " + razaoMorte + "!",
                "Fim de Jogo",
                JOptionPane.DEFAULT_OPTION, // Use default para tratar os botões do array
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );
    }
}