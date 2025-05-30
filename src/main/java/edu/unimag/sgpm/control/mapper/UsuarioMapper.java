package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.UsuarioDto;
import edu.unimag.sgpm.model.entity.Role;
import edu.unimag.sgpm.model.entity.Usuario;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "parqueadero.idParqueadero", target = "parqueadero")
    @Mapping(source = "idUsuario", target = "id")
    UsuarioDto toDto(Usuario usuario);

    @Mapping(source = "parqueadero", target = "parqueadero.idParqueadero")
    @Mapping(source = "id", target = "idUsuario")
    @Mapping(target = "contrasenia", ignore = true)
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(target = "motos", ignore = true)
    Usuario toEntity(UsuarioDto dto);

    List<UsuarioDto> toDtos(List<Usuario> usuarios);

    List<Usuario> toEntities(List<UsuarioDto> dtos);

    default Integer toIdRole(Role role){
        return role.getId();
    }

    default Role toRole(Integer id){
        return new Role(id,null);
    }

    Set<Integer> toIdRole(Set<Role> roles);
    Set<Role> toRoles(Set<Integer> roles);
}
