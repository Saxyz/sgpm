package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.reserva.ReservaDto;
import edu.unimag.sgpm.model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mapping(source = "idReserva", target = "id")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "usuario.idUsuario", target = "usuario")
    @Mapping(source = "espacio.idEspacio", target = "espacio")
    ReservaDto reservaToDto(Reserva reserva);

    @Mapping(source = "id", target = "idReserva")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "usuario", target = "usuario.idUsuario")
    @Mapping(source = "espacio", target = "espacio.idEspacio")
    Reserva reservaDtoToReserva(ReservaDto reserva);
}
