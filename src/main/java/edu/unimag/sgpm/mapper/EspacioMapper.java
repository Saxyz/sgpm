package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.espacio.EspacioDto;
import edu.unimag.sgpm.model.Espacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EspacioMapper {
    @Mapping(source = "parqueadero.idParqueadero", target = "parqueadero")
    @Mapping(source = "estado.idEstado", target = "estado")
    EspacioDto toDto(Espacio espacio);

    @Mapping(source = "parqueadero", target = "parqueadero.idParqueadero")
    @Mapping(source = "estado", target = "estado.idEstado")
    Espacio toEntity(EspacioDto espacioDto);
}
