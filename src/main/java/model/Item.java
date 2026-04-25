package model;

public enum Item {
    PLASTICO("plástico"), PAPEL("papel"), VIDRO("vidro"), METAL("metal"),
    ORGANICO("orgânico"), BOMBA("bomba"), MACA("maçã"), NENHUM("vazio");

    private final String nome;

    Item(String nome) {
        this.nome = nome;
    }

    public boolean ehLixo() {
        return this == PLASTICO || this == PAPEL || this == VIDRO || this == METAL || this == ORGANICO;
    }

    public String getNome() {
        return nome;
    }

    public static Item[] getLixos() {
        return new Item[]{PLASTICO, PAPEL, VIDRO, METAL, ORGANICO };
    }
}
