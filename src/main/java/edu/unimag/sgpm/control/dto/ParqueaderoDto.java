package edu.unimag.sgpm.control.dto;

import java.util.List;

public record ParqueaderoDto(Integer id, String nombre, List<String> espacios) {
}
