package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.registro.RegistroDto;

import java.util.List;

public interface RegistroService {
    RegistroDto createRegistro(RegistroDto registro);
    RegistroDto findRegistroById(Integer id);
    List<RegistroDto> findAllRegistros();
    RegistroDto updateRegistroById(Integer id, RegistroDto registro);
    void deleteRegistroById(int id);
}
