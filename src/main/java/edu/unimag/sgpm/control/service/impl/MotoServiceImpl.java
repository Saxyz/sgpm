package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.control.mapper.MotoMapper;
import edu.unimag.sgpm.model.entity.Moto;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.entity.Parqueadero;
import edu.unimag.sgpm.model.repository.MotoRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import edu.unimag.sgpm.model.repository.ParqueaderoRepository;
import edu.unimag.sgpm.control.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoServiceImpl implements MotoService {

    private final MotoRepository motoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final MotoMapper motoMapper;

    @Override
    public MotoDto createMoto(MotoDto request) {
        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Parqueadero parqueadero = parqueaderoRepository.findById(request.idParqueadero())
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));

        Moto moto = motoMapper.toEntity(request);
        moto.setIdMoto(request.idMoto());
        moto.setUsuario(usuario);
        moto.setParqueadero(parqueadero);

        Moto savedMoto = motoRepository.save(moto);
        return motoMapper.toDto(savedMoto);
    }

    @Override
    public MotoDto findMotoById(String id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        return motoMapper.toDto(moto);
    }

    @Override
    public List<MotoDto> findAllMotos() {
        List<Moto> motos = motoRepository.findAll();
        return motos.stream()
                .map(motoMapper::toDto).toList();
    }

    @Override
    public MotoDto updateMotoById(String id, MotoDto request) {
        motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        Moto moto;

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Parqueadero parqueadero = parqueaderoRepository.findById(request.idParqueadero())
                .orElseThrow(() -> new RuntimeException("Parqueadero no encontrado"));

        moto = motoMapper.toEntity(request);
        moto.setUsuario(usuario);
        moto.setParqueadero(parqueadero);

        Moto updatedMoto = motoRepository.save(moto);
        return motoMapper.toDto(updatedMoto);
    }

    @Override
    public void deleteMotoById(String id) {
        if (!motoRepository.existsById(id)) {
            throw new RuntimeException("Moto no encontrada para eliminar");
        }
        motoRepository.deleteById(id);
    }
}
