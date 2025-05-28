package edu.unimag.sgpm.control.dto;

import org.springframework.web.multipart.MultipartFile;

public record MotoDto(
        String idMoto,
        Integer idUsuario,
        Integer idParqueadero,
        String modelo,
        String descripcion,
        MultipartFile imagen,
        String ruta
) {
}
