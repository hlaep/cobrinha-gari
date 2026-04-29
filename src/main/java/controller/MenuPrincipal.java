package controller;

import view.Janela;
import view.MenuPrincipalView;

public class MenuPrincipal implements MenuPrincipalView.MenuListener {
    final private MenuPrincipalView menuPrincipal;
    final private Janela janelaPrincipal;

    public MenuPrincipal() {
        this.menuPrincipal = new MenuPrincipalView(Carregar.getImagem("/menuInicial.png"));
        this.janelaPrincipal = new Janela(this.menuPrincipal);
        janelaPrincipal.setVisible(true);

    }

    public void iniciar() {
        this.menuPrincipal.setListener(this);
    }

    public void voltarMenuPrincipal() {
        janelaPrincipal.setConteudo(menuPrincipal);
    }

    @Override
    public void onJogar() {
        Jogo j = new Jogo(janelaPrincipal, this);
        j.iniciar();
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