package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.EspacioDto;
import edu.unimag.sgpm.model.entity.Espacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspacioMapper {
    @Mapping(source = "idEspacio", target = "id")
    @Mapping(source = "parqueadero.idParqueadero", target = "parqueadero")
    @Mapping(source = "estado.idEstado", target = "estado")
    EspacioDto toDto(Espacio espacio);

    @Mapping(source = "id", target = "idEspacio")
    @Mapping(source = "parqueadero", target = "parqueadero.idParqueadero")
    @Mapping(source = "estado", target = "estado.idEstado")
    Espacio toEntity(EspacioDto espacioDto);

    List<Espacio> toEntities(List<EspacioDto> espacioDtos);

    List<EspacioDto> toDtos(List<Espacio> espacios);
}
