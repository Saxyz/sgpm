package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.model.entity.Moto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MotoMapper {

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    MotoDto toDto(Moto moto);

    @Mapping(source = "idUsuario", target = "usuario.idUsuario")
    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    Moto toEntity(MotoDto dto);

}
