package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.usuario.RequestUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.ResponseUsuarioDTO;
import edu.unimag.sgpm.control.dto.usuario.UpdateUsuarioDTO;
import edu.unimag.sgpm.model.entity.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "parqueadero.idParqueadero", target = "idParqueadero")
    ResponseUsuarioDTO toDto(Usuario usuario);

    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    Usuario toEntity(RequestUsuarioDTO dto);

    List<ResponseUsuarioDTO> toDtos(List<Usuario> usuarios);

    List<Usuario> toEntities(List<ResponseUsuarioDTO> dtos);

    @Mapping(source = "idParqueadero", target = "parqueadero.idParqueadero")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateUsuarioDTO dto, @MappingTarget Usuario usuario);
}
