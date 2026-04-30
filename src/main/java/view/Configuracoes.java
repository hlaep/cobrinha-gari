package view;

import java.awt.Dimension;

public class Configuracoes {
    public static final int ESPACO_TAMANHO = 32;
    public static final int tamanhoMapaQuadrado = 30;
    public static final int alturaPVDisplay = 32;
    private static final int dimensaoMapa = ESPACO_TAMANHO * tamanhoMapaQuadrado;

    public static Dimension getTamanhoJanela() {
        // PV não tem largura suficiente para exigir mais largura da janela //
        int altura =  dimensaoMapa + alturaPVDisplay;

        return new Dimension(dimensaoMapa, altura);
    }

    public static Dimension getTamanhoMapa() {
        return new Dimension(dimensaoMapa, dimensaoMapa);
    }
}
