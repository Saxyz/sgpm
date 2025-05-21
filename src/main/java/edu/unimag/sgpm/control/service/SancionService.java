package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.SancionDto;

import java.util.List;

public interface SancionService {
    SancionDto createSancion(SancionDto sancion);
    SancionDto findSancionById(Integer id);
    List<SancionDto> findAllSancion();
    SancionDto updateSancionById(Integer id, SancionDto sancion);
    void deleteSancionById(Integer id);
}
