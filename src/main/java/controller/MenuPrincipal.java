package controller;

import view.Janela;
import view.MenuPrincipalView;

public class MenuPrincipal implements MenuPrincipalView.MenuListener {
    final private MenuPrincipalView menuPrincipal;
    final private Janela janelaPrincipal;
    final private Jogo jogo;

    public MenuPrincipal() {
        this.menuPrincipal = new MenuPrincipalView(Carregar.getImagem("/menuInicial.png"));
        this.jogo = new Jogo( this);
        this.janelaPrincipal = new Janela(this.menuPrincipal, jogo.getTamanhoJogo());
    }

    public void iniciar() {
        this.menuPrincipal.setListener(this);
        janelaPrincipal.setVisible(true);
    }

    public void voltarMenuPrincipal() {
        janelaPrincipal.setConteudo(menuPrincipal);
    }

    @Override
    public void onJogar() {
        jogo.iniciar(janelaPrincipal);
    }

    @Override
    public void onClassificacao() {
        System.out.println("detectou botão classificação");

    }

    @Override
    public void onConfiguracoes() {
        System.out.println("detectou botão configurações");

    }
}