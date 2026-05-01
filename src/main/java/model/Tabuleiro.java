package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

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
    }

    private void renderizarColunaLixeiras() {
        int colunaCoordenada = DIMENSAO - 1; // Última //
        for(int i = 1; i < DIMENSAO - 1; i++) {
            // Preenche com arbusto entre a segunda e a penúltima (dimensão - 1) //
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

    private Espaco sortearEspacoVazio(List<Point> corpoCobrinha) {
        List<Point> espacosDisponiveis = new ArrayList<>();

        for(int i = 0; i < DIMENSAO; i++) {
            for(int j = 0; j < DIMENSAO; j++) {
                if(podeGerarColetavel(i, j, corpoCobrinha)) {
                    espacosDisponiveis.add(new Point(i, j));
                }
            }
        }

        Point sorteado = espacosDisponiveis.get(aleatorizar(espacosDisponiveis.size()));
        return mapa[sorteado.y][sorteado.x];
    }

    private boolean podeGerarColetavel(int x, int y, List<Point> corpoCobrinha) {
        Espaco espaco = mapa[y][x];
        for(Point parteCorpo: corpoCobrinha) {
            if(parteCorpo.x == x && parteCorpo.y == y) return false;
        }
        return espaco.getTipo() == EspacoTipo.ESPACO_VAZIO && espaco.getColetavel() == Coletavel.NENHUM;
    }

    public void gerarLixo(List<Point> corpoCobrinha) {
        Espaco espacoSorteado = sortearEspacoVazio(corpoCobrinha);
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

    public void gerarColetavel(Coletavel coletavel, List<Point> corpoCobrinha) {
        Espaco espacoSorteado = sortearEspacoVazio(corpoCobrinha);
        espacoSorteado.setColetavel(coletavel);
    }
}