package edu.unimag.sgpm.model.entity;

public enum Parqueaderos {
    Parqueadero1(1);

    private final int numero;

    Parqueaderos(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
