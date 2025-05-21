package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.ReservaDto;
import edu.unimag.sgpm.model.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mapping(source = "idReserva", target = "id")
    @Mapping(source = "estado.idEstado", target = "estado")
    @Mapping(source = "usuario.idUsuario", target = "usuario")
    @Mapping(source = "espacio.idEspacio", target = "espacio")
    ReservaDto reservaToDto(Reserva reserva);

    @Mapping(source = "id", target = "idReserva")
    @Mapping(source = "estado", target = "estado.idEstado")
    @Mapping(source = "usuario", target = "usuario.idUsuario")
    @Mapping(source = "espacio", target = "espacio.idEspacio")
    Reserva reservaDtoToReserva(ReservaDto reserva);

    List<ReservaDto> reservaListToDtoList(List<Reserva> reservas);
    List<Reserva> reservaListDtoToList(List<ReservaDto> reservas);
}
