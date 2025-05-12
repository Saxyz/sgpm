package edu.unimag.sgpm.control.exceptions;

import lombok.Data;

@Data
public class EspacioNotFoundException extends RuntimeException {
    public EspacioNotFoundException() {
        this("Espacio no econtontrado.");
    }
    public EspacioNotFoundException(String message) {
        super(message);
    }
    public EspacioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
