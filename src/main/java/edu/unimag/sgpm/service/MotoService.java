package edu.unimag.sgpm.service;

import edu.unimag.sgpm.dto.Moto.RequestMotoDTO;
import edu.unimag.sgpm.dto.Moto.MotoDTO;

import java.util.List;

public interface MotoService {

    MotoDTO createMoto(RequestMotoDTO request);

    MotoDTO findMotoById(String id);

    List<MotoDTO> findAllMotos();

    MotoDTO updateMotoById(String id, MotoDTO request);

    void deleteMotoById(String id);
}
