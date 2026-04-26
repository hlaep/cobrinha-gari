package model;

public enum Coletavel {
    LIXO_PLASTICO("lixo plástico"),
    LIXO_PAPEL("lixo papel"),
    LIXO_VIDRO("lixo vidro"),
    LIXO_METAL("lixo metal"),
    LIXO_ORGANICO("lixo orgânico"),
    BOMBA("bomba"),
    MACA("maçã"),
    NENHUM("vazio");

    private final String chaveString;

    Coletavel(String chaveString) {
        this.chaveString = chaveString;
    }

    public boolean ehLixo() {
        return this == LIXO_PLASTICO
                || this == LIXO_PAPEL
                || this == LIXO_VIDRO
                || this == LIXO_METAL
                || this == LIXO_ORGANICO;
    }

    public String getChaveString() {
        return chaveString;
    }

    public static Coletavel[] getLixos() {
        return new Coletavel[]{LIXO_PLASTICO, LIXO_PAPEL, LIXO_VIDRO, LIXO_METAL, LIXO_ORGANICO };
    }
}
