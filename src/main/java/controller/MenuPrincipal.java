package controller;

import view.Janela;

public class MenuPrincipal implements view.MenuPrincipal.MenuListener {
    final private view.MenuPrincipal menuPrincipal;
    final private Janela janelaPrincipal;

    public MenuPrincipal() {
        this.menuPrincipal = new view.MenuPrincipal(Carregar.getImagem("/menuInicial.png"));
        this.janelaPrincipal = new Janela(this.menuPrincipal);
        janelaPrincipal.setVisible(true);

    }

    public void iniciar() {
        this.menuPrincipal.setListener(this);
    }


    @Override
    public void onJogar() {
        Jogo j = new Jogo();
        j.iniciar(janelaPrincipal);
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