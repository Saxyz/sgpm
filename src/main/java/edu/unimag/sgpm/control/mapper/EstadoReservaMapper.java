package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.estadoReserva.EstadoReservaDto;
import edu.unimag.sgpm.model.entity.EstadoDeReserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoReservaMapper {
    EstadoReservaDto toDto(EstadoDeReserva estado);
    EstadoDeReserva toEntity(EstadoReservaDto estado);

    List<EstadoReservaDto> toDtos(List<EstadoDeReserva> estados);
    List<EstadoDeReserva> toEntities(List<EstadoReservaDto> estados);
}
