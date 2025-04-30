package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.espacio.EspacioDto;
import edu.unimag.sgpm.model.entity.Espacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspacioMapper {
    @Mapping(source = "idEspacio", target = "id")
    @Mapping(source = "parqueadero.idParqueadero", target = "parqueadero")
    EspacioDto toDto(Espacio espacio);

    @Mapping(source = "id", target = "idEspacio")
    @Mapping(source = "parqueadero", target = "parqueadero.idParqueadero")
    Espacio toEntity(EspacioDto espacioDto);

    List<Espacio> toEntities(List<EspacioDto> espacioDtos);

    List<EspacioDto> toDtos(List<Espacio> espacios);
}
