package edu.unimag.sgpm.service;

import edu.unimag.sgpm.dto.sancion.SancionDto;

import java.util.List;

public interface SancionService {
    SancionDto createSancion(SancionDto sancion);
    SancionDto findSancionById(int id);
    List<SancionDto> findAllSancion();
    SancionDto updateSancionById(SancionDto sancion);
    void deleteSancionById(int id);
}
