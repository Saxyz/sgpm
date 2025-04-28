package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    ResponseUsuarioDTO toDto(Usuario usuario);

    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    Usuario toEntity(RequestUsuarioDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateEntityFromDto(UpdateUsuarioDTO dto, @MappingTarget Usuario usuario);
}
