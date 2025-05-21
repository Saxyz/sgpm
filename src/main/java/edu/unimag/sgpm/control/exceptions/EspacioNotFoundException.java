package edu.unimag.sgpm.control.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EspacioNotFoundException extends RuntimeException {
    public EspacioNotFoundException(String message) {
        super(message);
    }
}
