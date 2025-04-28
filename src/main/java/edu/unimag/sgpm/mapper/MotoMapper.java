package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.Moto.RequestMotoDTO;
import edu.unimag.sgpm.dto.Moto.MotoDTO;
import edu.unimag.sgpm.model.Moto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MotoMapper {

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    MotoDTO toDto(Moto moto);

    @Mapping(source = "idUsuario", target = "usuario.idUsuario")
    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    Moto toEntity(RequestMotoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Moto updateEntityFromDto(MotoDTO dto, @MappingTarget Moto moto);
}
