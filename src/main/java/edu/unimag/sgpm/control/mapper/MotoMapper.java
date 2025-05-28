package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.model.entity.Moto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MotoMapper {

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    @Mapping(source = "imagen", target = "ruta")
    @Mapping(target = "imagen", ignore = true)
    MotoDto toDto(Moto moto);

    @Mapping(source = "idUsuario", target = "usuario.idUsuario")
    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    @Mapping(source = "ruta", target = "imagen")
    Moto toEntity(MotoDto dto);

}
