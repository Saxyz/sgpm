package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.sancion.SancionDto;
import edu.unimag.sgpm.control.mapper.SancionMapperImpl;
import edu.unimag.sgpm.control.service.SancionService;
import edu.unimag.sgpm.model.entity.Administrador;
import edu.unimag.sgpm.model.entity.Sancion;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.AdministradorRepository;
import edu.unimag.sgpm.model.repository.SancionRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SancionServiceImpl implements SancionService {
    private final UsuarioRepository usuarioRepository;
    private final SancionMapperImpl sancionMapperImpl;
    private final AdministradorRepository administradorRepository;
    private final SancionRepository sancionRepository;

    @Override
    public SancionDto createSancion(SancionDto sancionDto) {
        Usuario usuario = usuarioRepository.findById(sancionDto.sancionado())
                .orElseThrow(()-> new RuntimeException("Sancionado no encontrado"));
        Administrador administrador = administradorRepository.findById(sancionDto.sancionador())
                .orElseThrow(()-> new RuntimeException("Sancionar no encontrado"));
        Sancion sancion = sancionMapperImpl.toSancion(sancionDto);
        sancion.setSancionador(administrador);
        sancion.setSancionado(usuario);
        return sancionMapperImpl.toSancionDto(sancionRepository.save(sancion));
    }

    @Override
    public SancionDto findSancionById(Integer id) {
        return sancionMapperImpl.toSancionDto(sancionRepository.findById(id).orElseThrow(()-> new RuntimeException("Sancion no encontrada")));
    }

    @Override
    public List<SancionDto> findAllSancion() {
        return sancionMapperImpl.toSancionDtos(sancionRepository.findAll());
    }

    @Override
    public SancionDto updateSancionById(Integer id, SancionDto sancion) {
        Sancion antiguo = sancionRepository.findById(id).orElseThrow(()-> new RuntimeException("Sancion no encontrado"));
        antiguo.setDescripcion(sancion.descripcion());
        antiguo.setFecha(sancion.fecha());
        antiguo.setSancionado(usuarioRepository.findById(sancion.sancionado()).orElseThrow(() -> new RuntimeException("Sancionado no encontrado")));
        antiguo.setSancionador(administradorRepository.findById(sancion.sancionador()).orElseThrow(() -> new RuntimeException("Sancionar no encontrado")));
        return sancionMapperImpl.toSancionDto(sancionRepository.save(antiguo));
    }

    @Override
    public void deleteSancionById(Integer id) {
        sancionRepository.deleteById(id);
    }
}
