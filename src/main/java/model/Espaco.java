package model;

public class Espaco {
    private Item tipo;

    public Espaco(Item tipo) {
        this.tipo = tipo;
    }

    public void setTipo(Item tipo) {
        this.tipo = tipo;
    }

    public Item getTipo() {
        return tipo;
    }
}