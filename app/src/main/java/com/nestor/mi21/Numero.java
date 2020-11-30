package com.nestor.mi21;

public class Numero {
    private String numero;
    private int carta;

    public Numero(String numero, int carta) {
        this.numero = numero;
        this.carta = carta;
    }

    public Numero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCarta() {
        return carta;
    }

    public void setCarta(int carta) {
        this.carta = carta;
    }
}
