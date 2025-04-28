package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.reserva.ReservaDto;
import edu.unimag.sgpm.model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "estado.idEstado", target = "estado")
    @Mapping(source = "usuario.idUsuario", target = "usuario")
    ReservaDto reservaToDto(Reserva reserva);

    @Mapping(source = "estado", target = "estado.idEstado")
    @Mapping(source = "usuario", target = "usuario.idUsuario")
    Reserva reservaDtoToReserva(ReservaDto reserva);
}
