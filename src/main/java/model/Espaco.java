package model;

public class Espaco {
    private final EspacoTipo tipo;
    private Coletavel coletavel; // Se for espaço vazio é nenhum //
    private Coletavel aceitaEntrega;  // Se não for ponto de entrega é nenhum //

    public Espaco(EspacoTipo tipo) {
        this.tipo = tipo;
        this.coletavel = Coletavel.NENHUM;
        this.aceitaEntrega = Coletavel.NENHUM;
    }

    public EspacoTipo getTipo() {
        return tipo;
    }

    public Coletavel getColetavel() {
        return coletavel;
    }

    public void setColetavel(Coletavel novoColetavel) {
        if(tipo == EspacoTipo.ESPACO_VAZIO) coletavel = novoColetavel;
    }

    public void setAceitaEntrega(Coletavel tipoDeEntrega) {
        if(tipo == EspacoTipo.PONTO_ENTREGA) aceitaEntrega = tipoDeEntrega;
    }

    public boolean verificarSeAceitaEntrega(Coletavel lixo) {
        return lixo == aceitaEntrega;
    }
}