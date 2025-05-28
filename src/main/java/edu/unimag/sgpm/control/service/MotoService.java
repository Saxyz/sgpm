package edu.unimag.sgpm.control.service;

import edu.unimag.sgpm.control.dto.MotoDto;

import java.util.List;

public interface MotoService {

    MotoDto createMoto(MotoDto request);

    MotoDto findMotoById(Integer id);

    List<MotoDto> findAllMotos();

    MotoDto updateMotoById(Integer id, MotoDto request);

    void deleteMotoById(Integer id);
}
