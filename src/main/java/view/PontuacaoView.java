package view;

import model.Pontuacao;
import javax.swing.JLabel;

public class PontuacaoView {
    private final Pontuacao pontuacao;
    private final JLabel pontuacaoLabel;

    public PontuacaoView(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
        pontuacaoLabel = new JLabel(Integer.toString(pontuacao.getPontuacao()));
    }

    public void atualizarPontuacao() {
        pontuacaoLabel.setText(Integer.toString(pontuacao.getPontuacao()));
    }
}
