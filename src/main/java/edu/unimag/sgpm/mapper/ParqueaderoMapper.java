package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.parqueadero.RequestParqueaderoDTO;
import edu.unimag.sgpm.dto.parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.dto.parqueadero.UpdateParqueaderoDTO;
import edu.unimag.sgpm.model.Parqueadero;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParqueaderoMapper {

    @Mapping(source = "idParqueadero", target = "idParqueadero")
    ResponseParqueaderoDTO toDto(Parqueadero parqueadero);

    Parqueadero toEntity(RequestParqueaderoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Parqueadero updateEntityFromDto(UpdateParqueaderoDTO dto, @MappingTarget Parqueadero parqueadero);
}
