package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.model.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    ResponseUsuarioDTO toDto(Usuario usuario);

    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    Usuario toEntity(RequestUsuarioDTO dto);

    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateEntityFromDto(UpdateUsuarioDTO dto, @MappingTarget Usuario usuario);
}
