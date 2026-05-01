package model;

public enum EspacoTipo {
    ARBUSTO("arbusto"),
    LIXEIRA_METAL("lixeira-metal"),
    LIXEIRA_PAPEL("lixeira-papel"),
    LIXEIRA_PLASTICO("lixeira-plastico"),
    LIXEIRA_VIDRO("lixeira-vidro"),
    LIXEIRA_ORGANICO("lixeira-organico"),
    ESPACO_VAZIO("espaco-vazio"),
    PONTO_ENTREGA("espaco-vazio");

    private final String chaveString;

    EspacoTipo(String chaveString) {
        this.chaveString = chaveString;
    }

    public String getChaveString() {
        return chaveString;
    }

    public boolean ehIntransitavel() {
        return this != ESPACO_VAZIO && this != PONTO_ENTREGA;
    }

    public static EspacoTipo[] getLixeiras() {
        return new EspacoTipo[] {LIXEIRA_METAL, LIXEIRA_PAPEL, LIXEIRA_PLASTICO, LIXEIRA_VIDRO, LIXEIRA_ORGANICO};
    }

    public boolean ehLixeira() {
        return this == LIXEIRA_METAL || this == LIXEIRA_ORGANICO || this == LIXEIRA_PAPEL || this == LIXEIRA_PLASTICO || this == LIXEIRA_VIDRO;
    }

    public Coletavel getColetavelEquivalente() {
        return switch(this) {
            case LIXEIRA_METAL -> Coletavel.LIXO_METAL;
            case LIXEIRA_PLASTICO -> Coletavel.LIXO_PLASTICO;
            case LIXEIRA_VIDRO -> Coletavel.LIXO_VIDRO;
            case LIXEIRA_PAPEL -> Coletavel.LIXO_PAPEL;
            case LIXEIRA_ORGANICO -> Coletavel.LIXO_ORGANICO;
            default -> null;
        };
    }
}
