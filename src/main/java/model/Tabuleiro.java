package model;

import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro {
    private final int DIMENSAO = 30;
    private final Espaco[][] mapa;

    public Tabuleiro() {
        mapa = new Espaco[DIMENSAO][DIMENSAO];

        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                if(i == 0 || i == DIMENSAO - 1 || j == 0) {
                        // Põe arbustos nas bordas //
                        mapa[i][j] = new Espaco(EspacoTipo.ARBUSTO);
                } else {
                    // Espaço normal //
                    mapa[i][j] = new Espaco(EspacoTipo.ESPACO_VAZIO);
                }
            }
        }
        renderizarColunaLixeiras();
        gerarLixo();
    }

    private void renderizarColunaLixeiras() {
        int colunaCoordenada = DIMENSAO - 1; // Última //
        for(int i = 1; i < DIMENSAO - 1; i++) {
            // Preenche com arbusto entre a sengunda e a penúltima (dimensão - 1) //
            mapa[i][colunaCoordenada] = new Espaco(EspacoTipo.ARBUSTO);
        }

        EspacoTipo[] lixeiras = EspacoTipo.getLixeiras();

        int linhaAtual = 1;
        for(EspacoTipo tipoLixeira: lixeiras) {
            mapa[linhaAtual][colunaCoordenada] = new Espaco(tipoLixeira);
            Coletavel lixoEquivalente = tipoLixeira.getColetavelEquivalente();
            // Espaço vazio para a cobrinha conseguir passar e entregar o lixo //
            mapa[linhaAtual + 1][colunaCoordenada] = new Espaco(EspacoTipo.PONTO_ENTREGA);
            mapa[linhaAtual + 1][colunaCoordenada].setAceitaEntrega(lixoEquivalente);
            // Abertura para a cobrinha conseguir teleportar para o outro lado //
            mapa[linhaAtual + 1][0] = new Espaco(EspacoTipo.ESPACO_VAZIO);
            // +3 (lixeira, espaço de entrega e arbusto separando) //
            linhaAtual += 3;
        }
    }

    private int aleatorizar(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    public void gerarLixo() {
        Espaco espacoSorteado = mapa[aleatorizar(DIMENSAO)][aleatorizar(DIMENSAO)];

        while(espacoSorteado.getTipo() != EspacoTipo.ESPACO_VAZIO) {
            espacoSorteado = mapa[aleatorizar(DIMENSAO)][aleatorizar(DIMENSAO)];
        }

        Coletavel[] lixos = Coletavel.getLixos();
        Coletavel lixoSorteado = lixos[aleatorizar(lixos.length)];

       espacoSorteado.setColetavel(lixoSorteado);
    }

    public Espaco[][] getMapa() {
        // A primeira é o y e a segunda é o x mapa[y][x] //
        return mapa;
    }

    public int getDIMENSAO() {
        return DIMENSAO;
    }

    public void limparLixosMapa() {
        for(int i = 0; i < DIMENSAO; i++) {
            for(int j = 0; j < DIMENSAO; j++) {

                if(mapa[i][j].getTipo() == EspacoTipo.ESPACO_VAZIO) {
                    mapa[i][j].setColetavel(Coletavel.NENHUM);
                }

            }
        }
    }
}