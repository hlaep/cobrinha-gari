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
}
