package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.EstadoEspacioDto;
import edu.unimag.sgpm.model.entity.EstadoDeEspacio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoEspacioMapper {

    EstadoEspacioDto toDto(EstadoDeEspacio estado);
    EstadoDeEspacio toEntity(EstadoEspacioDto estado);

    List<EstadoEspacioDto> toDtos(List<EstadoDeEspacio> estados);
    List<EstadoDeEspacio> toEntities(List<EstadoEspacioDto> estado);
}
