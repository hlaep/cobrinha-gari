package controller;

import model.Direcao;
import model.EspacoTipo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carregar {
    public static Image getImagem(String caminho) {
        URL imgURL = Carregar.class.getResource(caminho);

        if (imgURL == null) {
            System.err.println("Recurso não encontrado: " + caminho);
            return null;
        }

        try { return ImageIO.read(imgURL); }
        catch (IOException e) {
            System.err.println("Erro ao ler a imagem: " + e.getMessage());
            return null;
        }
    }

    public static Map<String, Image> getArtesMapa() {
        Map<String, Image> artes = new HashMap<>();

        EspacoTipo[] espacoTipos = EspacoTipo.values();
        for(EspacoTipo tipo: espacoTipos) {
            String chave = tipo.getChaveString();
            artes.put(chave, getImagem("/" + chave + ".png"));
        }

        artes.put("explosão", getImagem("/explosao.png"));
        artes.put("lixo metal", getImagem("/lixo-metal.png"));
        artes.put("lixo orgânico", getImagem("/lixo-organico.png"));
        artes.put("lixo papel", getImagem("/lixo-papel.png"));
        artes.put("lixo vidro", getImagem("/lixo-vidro.png"));
        artes.put("lixo plástico", getImagem("/lixo-plastico.png"));
        artes.put("maçã", getImagem("/espaco-maca.png"));
        artes.put("bomba", getImagem("/espaco-bomba.png"));

        artes.put("cabeça leste", getImagem("/cobrinha/cabeca-leste.png"));
        artes.put("cabeça oeste", getImagem("/cobrinha/cabeca-oeste.png"));
        artes.put("cabeça norte", getImagem("/cobrinha/cabeca-norte.png"));
        artes.put("cabeça sul", getImagem("/cobrinha/cabeca-sul.png"));

        artes.put("corpo horizontal", getImagem("/cobrinha/corpo-horizontal.png"));
        artes.put("corpo vertical", getImagem("/cobrinha/corpo-vertical.png"));

        artes.put("corpo virando norte leste", getImagem("/cobrinha/corpo-virando-norte-leste.png"));
        artes.put("corpo virando oeste sul", getImagem("/cobrinha/corpo-virando-oeste-sul.png"));
        artes.put("corpo virando oeste norte", getImagem("/cobrinha/corpo-virando-oeste-norte.png"));
        artes.put("corpo virando leste sul", getImagem("/cobrinha/corpo-virando-leste-sul.png"));

        artes.put("rabo leste", getImagem("/cobrinha/rabo-leste.png"));
        artes.put("rabo oeste", getImagem("/cobrinha/rabo-oeste.png"));
        artes.put("rabo norte", getImagem("/cobrinha/rabo-norte.png"));
        artes.put("rabo sul", getImagem("/cobrinha/rabo-sul.png"));

        return artes;
    }

    // Pegar keys para artes da cobrinha //
    public static List<String> getKeyArteCorpoCobrinha(List<Point> corpo, Direcao direcaoCabeca) {
        // corpo começa da cabeça[0] até o rabo[size - 1] //
        List<String> keys;
        keys = new ArrayList<>();

        for(int i = 0; i < corpo.size(); i++) {
            if(i == 0) {
                keys.addLast(getKeyArteCabeca(direcaoCabeca));
            } else {
                Point proximoPonto = corpo.get(i - 1);
                Point pontoAtual = corpo.get(i);

                if(i == corpo.size() - 1) keys.addLast(getKeyArteRabo(proximoPonto, pontoAtual));
                else {
                    Point pontoAnterior = corpo.get(i + 1);
                    keys.addLast(getKeyArtePontoAtual(pontoAnterior, pontoAtual, proximoPonto));
                }

            }
        }
        return keys;
    }

    private static String getKeyArteCabeca(Direcao direcao) {
        return "cabeça " + direcao.getNome();
    }

    private static String getKeyArtePontoAtual(Point anterior, Point atual, Point proximo) {
        Direcao direcaoProximoPonto = getDirecao(proximo, atual);
        Direcao direcaoPontoAnterior = getDirecao(anterior, atual);

        boolean proximaEhNorte = direcaoProximoPonto == Direcao.NORTE;
        boolean proximaEhSul = direcaoProximoPonto == Direcao.SUL;
        boolean proximaEhLeste = direcaoProximoPonto == Direcao.LESTE;
        boolean proximaEhOeste = direcaoProximoPonto == Direcao.OESTE;

        boolean anteriorEhNorte = direcaoPontoAnterior == Direcao.NORTE;
        boolean anteriorEhSul = direcaoPontoAnterior == Direcao.SUL;
        boolean anteriorEhLeste = direcaoPontoAnterior == Direcao.LESTE;
        boolean anteriorEhOeste = direcaoPontoAnterior == Direcao.OESTE;

        boolean algumEhHorizontal = anteriorEhLeste || anteriorEhOeste || proximaEhLeste || proximaEhOeste;

        if(getDirecao(proximo, atual).ehOposta(direcaoPontoAnterior)) {
            /* Se proximo e atual são direções opostas ao atual a parte do corpo é reta */
            return getKeyArteCorpoReto(algumEhHorizontal);
        } else {
            // Se direçoes não são iguais, certamente há uma curva. //
            return getKeyArteCorpoVirando(
                    proximaEhNorte,
                    proximaEhSul,
                    proximaEhLeste,
                    proximaEhOeste,
                    anteriorEhNorte,
                    anteriorEhSul,
                    anteriorEhLeste,
                    anteriorEhOeste
            );
        }
    }

    private static Direcao getDirecao(Point outro, Point atual) {
        // retorna direção de um ponto em relação a um ponto atual //
        if(outro.x < atual.x) return Direcao.OESTE;
        if(outro.x > atual.x) return Direcao.LESTE;
        if(outro.y < atual.y) return Direcao.NORTE;
        if(outro.y > atual.y) return Direcao.SUL;
        return null;
    }

    private static String getKeyArteCorpoReto(boolean ehHorizontal) {
        if(ehHorizontal) return "corpo horizontal";
        else return "corpo vertical"; // Não sendo horizontal, então certamente é vertical.
    }

    private static String getKeyArteCorpoVirando(
            boolean proximaEhNorte,
            boolean proximaEhSul,
            boolean proximaEhLeste,
            boolean proximaEhOeste,
            boolean anteriorEhNorte,
            boolean anteriorEhSul,
            boolean anteriorEhLeste,
            boolean anteriorEhOeste
    ) {
        String prefixo = "corpo virando ";
        if ((proximaEhNorte && anteriorEhLeste) || (proximaEhLeste && anteriorEhNorte))
            // Em ambos os casos a arte é igual //
            return prefixo + "norte leste";
        if((proximaEhNorte && anteriorEhOeste) || (proximaEhOeste && anteriorEhNorte))
            return prefixo + "oeste norte";

        if((proximaEhSul && anteriorEhLeste) || (proximaEhLeste && anteriorEhSul))
            return prefixo + "leste sul";
        if((proximaEhSul && anteriorEhOeste) || (proximaEhOeste && anteriorEhSul))
            return prefixo + "oeste sul";

        return null;
    }

    private static String getKeyArteRabo(Point proximo, Point atual) {
        Direcao posicaoProximoPontoRelativoAoRabo = getDirecao(proximo, atual);
        return "rabo " + posicaoProximoPontoRelativoAoRabo.getNome();
    }
}