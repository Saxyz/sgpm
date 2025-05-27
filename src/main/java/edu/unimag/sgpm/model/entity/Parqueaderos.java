package edu.unimag.sgpm.model.entity;

import lombok.Getter;

@Getter
public enum Parqueaderos {
    Parqueadero1(1);

    private final int numero;

    Parqueaderos(int numero) {
        this.numero = numero;
    }

}
