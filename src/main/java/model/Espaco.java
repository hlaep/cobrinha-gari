package model;

public class Espaco {
    private final EspacoTipo tipo;
    private Coletavel coletavel; // Pode ser Coletavel.NENHUM

    public Espaco(EspacoTipo tipo) {
        this.tipo = tipo;
        this.coletavel = Coletavel.NENHUM;
    }


    // public boolean podeCaminhar() {return this.tipo != EspacoTipo.ARBUSTO && this.tipo != EspacoTipo.PAREDE;}

    public EspacoTipo getTipo() {
        return tipo;
    }

    public Coletavel getColetavel() {
        return coletavel;
    }

    public void setColetavel(Coletavel c) {
        coletavel = c;
    }

}