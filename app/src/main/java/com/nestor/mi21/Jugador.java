package com.nestor.mi21;

public class Jugador {
    private int foto;
    private int  id;
    private String nombre;
    private int numero;
    private String created_at;
    private String updated_at;

    public Jugador(int foto, int id, String nombre, int numero, String created_at, String updated_at) {
        this.foto = foto;
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
