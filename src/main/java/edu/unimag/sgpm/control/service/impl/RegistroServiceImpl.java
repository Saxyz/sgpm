package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.RegistroDto;
import edu.unimag.sgpm.control.mapper.RegistroMapper;
import edu.unimag.sgpm.control.service.RegistroService;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.Moto;
import edu.unimag.sgpm.model.entity.Registro;
import edu.unimag.sgpm.model.entity.Vigilante;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.MotoRepository;
import edu.unimag.sgpm.model.repository.RegistroRepository;
import edu.unimag.sgpm.model.repository.VigilanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroServiceImpl implements RegistroService {

    private final EspacioRepository espacioRepository;
    private final MotoRepository motoRepository;
    private final VigilanteRepository vigilanteRepository;
    private final RegistroMapper registroMapper;
    private final RegistroRepository registroRepository;

    @Override
    public RegistroDto createRegistro(RegistroDto registroDto) {
        Espacio espacio = espacioRepository.findById(registroDto.espacio())
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));
        Moto moto = motoRepository.findById(registroDto.moto())
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        Vigilante vigilante = vigilanteRepository.findById(registroDto.vigilante())
                .orElseThrow(() -> new RuntimeException("Vigilante no encontrado"));
        Registro registro = registroMapper.toRegistro(registroDto);
        registro.setEspacio(espacio);
        registro.setMoto(moto);
        registro.setVigilante(vigilante);
        return registroMapper.toDto(registroRepository.save(registro));
    }

    @Override
    public RegistroDto findRegistroById(Integer id) {
        return registroMapper.toDto(registroRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro no encontrado")));
    }

    @Override
    public List<RegistroDto> findAllRegistros() {
        return registroMapper.toDtos(registroRepository.findAll());
    }

    @Override
    public RegistroDto updateRegistroById(Integer id, RegistroDto registro) {
        Registro antiguo = registroRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        antiguo.setMoto(motoRepository.findById(registro.moto()).orElseThrow(() -> new RuntimeException("Moto no encontrado")));
        antiguo.setEspacio(espacioRepository.findById(registro.espacio()).orElseThrow(() -> new RuntimeException("Espacio no encontrado")));
        antiguo.setVigilante(vigilanteRepository.findById(registro.vigilante()).orElseThrow(() -> new RuntimeException("Vigilante no encontrado")));
        return registroMapper.toDto(registroRepository.save(antiguo));
    }

    @Override
    public void deleteRegistroById(int id) {
        registroRepository.deleteById(id);
    }
}
