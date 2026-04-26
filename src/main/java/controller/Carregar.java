package controller;

import model.EspacoTipo;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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

    public static Image getImagem(String caminho, int larguraDesejada) {
        Image imgOriginal = getImagem(caminho);

        if (imgOriginal == null) {
            return null;
        }

        // Obtemos as dimensões originais
        int larguraOriginal = imgOriginal.getWidth(null);
        int alturaOriginal = imgOriginal.getHeight(null);

        // Calculamos a nova altura mantendo a proporção
        // Usamos double para o cálculo para não perder precisão antes do arredondamento
        int novaAltura = (int) (((double) larguraDesejada / larguraOriginal) * alturaOriginal);

        // Retorna a imagem redimensionada
        return imgOriginal.getScaledInstance(larguraDesejada, novaAltura, Image.SCALE_SMOOTH);
    }


    public static Map<String, Image> getArtes() {
        Map<String, Image> artes = new HashMap<>();

        EspacoTipo[] espacoTipos = EspacoTipo.values();
        for(EspacoTipo tipo: espacoTipos) {
            String chave = tipo.getChaveString();
            artes.put(chave, getImagem("/" + chave + ".png"));
        }

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

        artes.put("corpo virando leste", getImagem("/cobrinha/corpo-virando-leste.png"));
        artes.put("corpo virando oeste", getImagem("/cobrinha/corpo-virando-oeste.png"));
        artes.put("corpo virando norte", getImagem("/cobrinha/corpo-virando-norte.png"));
        artes.put("corpo virando sul", getImagem("/cobrinha/corpo-virando-sul.png"));

        artes.put("rabo leste", getImagem("/cobrinha/rabo-leste.png"));
        artes.put("rabo oeste", getImagem("/cobrinha/rabo-oeste.png"));
        artes.put("rabo norte", getImagem("/cobrinha/rabo-norte.png"));
        artes.put("rabo sul", getImagem("/cobrinha/rabo-sul.png"));

        return artes;
    }
}