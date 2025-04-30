package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.espacio.EspacioDto;

import java.util.List;

public interface EspacioService {
    EspacioDto createEspacio(EspacioDto espacio);
    EspacioDto findEspacioById(String id);
    List<EspacioDto> findAllEspacios();
    EspacioDto updateEspacio(String id, EspacioDto espacio);
    void deleteEspacioById(String id);
}
