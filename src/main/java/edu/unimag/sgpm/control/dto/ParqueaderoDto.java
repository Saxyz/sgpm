package edu.unimag.sgpm.control.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ParqueaderoDto(Integer id, String nombre, MultipartFile imagen, String ruta, List<Integer> espacios) {
}
