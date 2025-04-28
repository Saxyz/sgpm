package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.registro.RegistroDto;
import edu.unimag.sgpm.model.Registro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroMapper {

    @Mapping(source = "espacio.idEspacio", target = "espacio")
    @Mapping(source = "moto.idMoto", target = "moto")
    @Mapping(source = "vigilante.idUsuario", target = "vigilante")
    RegistroDto toDto(Registro registro);

    @Mapping(source = "espacio", target = "espacio.idEspacio")
    @Mapping(source = "moto", target = "moto.idMoto")
    @Mapping(source = "vigilante", target = "vigilante.idUsuario")
    Registro toRegistro(RegistroDto registroDto);
}
