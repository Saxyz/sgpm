package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.EspacioDto;

import java.util.List;

public interface EspacioService {
    EspacioDto createEspacio(EspacioDto espacio);
    EspacioDto findEspacioById(Integer id);
    List<EspacioDto> findAllEspacios();
    EspacioDto updateEspacio(Integer id, EspacioDto espacio);
    void deleteEspacioById(Integer id);
}
