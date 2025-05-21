package edu.unimag.sgpm.control.service.impl;

import edu.unimag.sgpm.control.dto.ReservaDto;
import edu.unimag.sgpm.control.mapper.ReservaMapperImpl;
import edu.unimag.sgpm.control.service.ReservaService;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import edu.unimag.sgpm.model.entity.Reserva;
import edu.unimag.sgpm.model.entity.Usuario;
import edu.unimag.sgpm.model.repository.EspacioRepository;
import edu.unimag.sgpm.model.repository.EstadoReservaRepository;
import edu.unimag.sgpm.model.repository.ReservaRepository;
import edu.unimag.sgpm.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    private final EstadoReservaRepository estadoReservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspacioRepository espacioRepository;
    private final ReservaMapperImpl reservaMapperImpl;
    private final ReservaRepository reservaRepository;

    @Override
    public ReservaDto createReserva(ReservaDto reservaDto) {
        EstadoDeReserva estado = estadoReservaRepository.findById(reservaDto.estado())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Usuario usuario = usuarioRepository.findById(reservaDto.id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Espacio espacio = espacioRepository.findById(reservaDto.espacio())
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));
        Reserva reserva = reservaMapperImpl.reservaDtoToReserva(reservaDto);
        reserva.setEstado(estado);
        reserva.setUsuario(usuario);
        reserva.setEspacio(espacio);
        return reservaMapperImpl.reservaToDto(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDto findReservaById(Integer id) {
        return reservaMapperImpl.reservaToDto(reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada")));
    }

    @Override
    public List<ReservaDto> findAllReservas() {
        return reservaMapperImpl.reservaListToDtoList(reservaRepository.findAll());
    }

    @Override
    public ReservaDto updateReservaById(Integer id, ReservaDto reservaDto) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEspacio(espacioRepository.findById(reservaDto.espacio()).orElseThrow(() -> new RuntimeException("Espacio no encontrado")));
        reserva.setUsuario(usuarioRepository.findById(reservaDto.usuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        reserva.setEstado(estadoReservaRepository.findById(reservaDto.estado()).orElseThrow(() -> new RuntimeException("Estado no encontrado")));
        return reservaMapperImpl.reservaToDto(reservaRepository.save(reserva));
    }

    @Override
    public void deleteReservaById(Integer id) {
        reservaRepository.deleteById(id);
    }
}
