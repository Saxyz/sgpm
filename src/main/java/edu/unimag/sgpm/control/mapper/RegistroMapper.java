package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.RegistroDto;
import edu.unimag.sgpm.model.entity.Registro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistroMapper {
    @Mapping(source = "idRegistro", target = "id")
    @Mapping(source = "espacio.idEspacio", target = "espacio")
    @Mapping(source = "moto.idMoto", target = "moto")
    @Mapping(source = "vigilante.idUsuario", target = "vigilante")
    RegistroDto toDto(Registro registro);

    @Mapping(source = "id", target = "idRegistro")
    @Mapping(source = "espacio", target = "espacio.idEspacio")
    @Mapping(source = "moto", target = "moto.idMoto")
    @Mapping(source = "vigilante", target = "vigilante.idUsuario")
    Registro toRegistro(RegistroDto registroDto);

    List<RegistroDto> toDtos(List<Registro> registros);

    List<Registro> toRegistros(List<RegistroDto> registroDtos);
}
