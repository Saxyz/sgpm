package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.Parqueadero.RequestParqueaderoDTO;
import edu.unimag.sgpm.dto.Parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.dto.Parqueadero.UpdateParqueaderoDTO;
import edu.unimag.sgpm.model.Parqueadero;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParqueaderoMapper {

    ResponseParqueaderoDTO toDto(Parqueadero parqueadero);

    Parqueadero toEntity(RequestParqueaderoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Parqueadero updateEntityFromDto(UpdateParqueaderoDTO dto, @MappingTarget Parqueadero parqueadero);
}
