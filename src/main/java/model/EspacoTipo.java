package model;

public enum EspacoTipo {
    ARBUSTO("arbusto"),
    LIXEIRA_METAL("lixeira-metal"),
    LIXEIRA_PAPEL("lixeira-papel"),
    LIXEIRA_PLASTICO("lixeira-plastico"),
    LIXEIRA_VIDRO("lixeira-vidro"),
    LIXEIRA_ORGANICO("lixeira-organico"),
    ESPACO_VAZIO("espaco-vazio");

    private final String chaveString;

    EspacoTipo(String chaveString) {
        this.chaveString = chaveString;
    }

    public String getChaveString() {
        return chaveString;
    }

    public boolean ehIntransitavel() {
        return this != ESPACO_VAZIO;
    }

    public static EspacoTipo[] getLixeiras() {
        return new EspacoTipo[] {LIXEIRA_METAL, LIXEIRA_PAPEL, LIXEIRA_PLASTICO, LIXEIRA_VIDRO, LIXEIRA_ORGANICO};
    }
}
