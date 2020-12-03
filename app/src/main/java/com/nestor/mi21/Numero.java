package com.nestor.mi21;

public class Numero {
    private int numero;
    private int carta;

    public Numero(int numero, int carta) {
        this.numero = numero;
        this.carta = carta;
    }

    public Numero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCarta() {
        return carta;
    }

    public void setCarta(int carta) {
        this.carta = carta;
    }
}
