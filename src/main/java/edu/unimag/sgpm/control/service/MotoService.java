package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.MotoDto;

import java.util.List;

public interface MotoService {

    MotoDto createMoto(MotoDto request);

    MotoDto findMotoById(String id);

    List<MotoDto> findAllMotos();

    MotoDto updateMotoById(String id, MotoDto request);

    void deleteMotoById(String id);
}
