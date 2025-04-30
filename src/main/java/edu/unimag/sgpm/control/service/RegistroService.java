package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.registro.RegistroDto;

import java.util.List;

public interface RegistroService {
    RegistroDto createRegistro(RegistroDto registro);
    RegistroDto findRegistroById(int id);
    List<RegistroDto> findAllRegistros();
    RegistroDto updateRegistroById(int id, RegistroDto registro);
    void deleteRegistroById(int id);
}
