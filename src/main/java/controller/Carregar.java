package controller;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

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
}