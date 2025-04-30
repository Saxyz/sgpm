package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.parqueadero.RequestParqueaderoDTO;
import edu.unimag.sgpm.control.dto.parqueadero.ResponseParqueaderoDTO;
import edu.unimag.sgpm.control.dto.parqueadero.UpdateParqueaderoDTO;
import edu.unimag.sgpm.model.entity.Parqueadero;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParqueaderoMapper {

    @Mapping(source = "idParqueadero", target = "idParqueadero")
    ResponseParqueaderoDTO toDto(Parqueadero parqueadero);

    Parqueadero toEntity(RequestParqueaderoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Parqueadero updateEntityFromDto(UpdateParqueaderoDTO dto, @MappingTarget Parqueadero parqueadero);
}
