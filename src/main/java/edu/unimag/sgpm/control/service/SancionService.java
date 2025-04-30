package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.sancion.SancionDto;

import java.util.List;

public interface SancionService {
    SancionDto createSancion(SancionDto sancion);
    SancionDto findSancionById(int id);
    List<SancionDto> findAllSancion();
    SancionDto updateSancionById(SancionDto sancion);
    void deleteSancionById(int id);
}
