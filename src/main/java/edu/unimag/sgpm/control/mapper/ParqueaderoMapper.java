package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.ParqueaderoDto;
import edu.unimag.sgpm.model.entity.Espacio;
import edu.unimag.sgpm.model.entity.Parqueadero;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParqueaderoMapper {

    @Mapping(source = "idParqueadero", target = "id")
    @Mapping(source = "imagen", target = "ruta")
    @Mapping(target = "imagen", ignore = true)
    ParqueaderoDto toDto(Parqueadero parqueadero);

    @Mapping(source = "id", target = "idParqueadero")
    @Mapping(source = "espacios", target = "espacios", ignore = true)
    @Mapping(source = "ruta", target = "imagen")
    Parqueadero toEntity(ParqueaderoDto dto);

    @Mapping(source = "espacio", target = "idEspacio")
    Espacio toEspacio(String espacio);

    String toIdEspacio(Espacio espacio);

    List<Espacio> toEspacios(List<String> espacios);
    List<String> toIdEspacio(List<Espacio> espacios);
}
