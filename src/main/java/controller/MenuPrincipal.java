package controller;

import view.Janela;
import view.Jogo;
import view.Mapa;

import model.Tabuleiro;

import java.awt.Image;
import java.util.Map;

public class MenuPrincipal implements view.MenuPrincipal.MenuListener {
    final private view.MenuPrincipal menuPrincipal;
    final private Janela janelaPrincipal;
    private final Tabuleiro tabuleiro;

    public MenuPrincipal() {
        this.tabuleiro = new Tabuleiro();
        this.menuPrincipal = new view.MenuPrincipal(Carregar.getImagem("/menuInicial.png"));
        this.janelaPrincipal = new Janela(this.menuPrincipal);
        janelaPrincipal.setVisible(true);

    }

    public void iniciar() {
        this.menuPrincipal.setListener(this);
    }

    private Map<String, Image> carregarRecursosMapa() {
        return Map.of(
                "vazio", Carregar.getImagem("/terreno-vazio.png"),
                "vidro", Carregar.getImagem("/lixo-vidro.png"),
                "plástico", Carregar.getImagem("/lixo-plastico.png"),
                "papel", Carregar.getImagem("/lixo-papel.png"),
                "metal", Carregar.getImagem("/lixo-metal.png"),
                "orgânico", Carregar.getImagem("/lixo-organico.png")
        );
    }

    @Override
    public void onJogar() {
        Mapa m = new Mapa(carregarRecursosMapa(), tabuleiro.getMapa());
        this.janelaPrincipal.setConteudo(new Jogo(m));
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