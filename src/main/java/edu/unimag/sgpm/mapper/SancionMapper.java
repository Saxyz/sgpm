package edu.unimag.sgpm.mapper;

import edu.unimag.sgpm.dto.sancion.SancionDto;
import edu.unimag.sgpm.model.Sancion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SancionMapper {

    @Mapping(source = "sancionado.idUsuario", target = "sancionado")
    @Mapping(source = "sancionador.idUsuario", target = "sancionador")
    SancionDto toSancionDto(Sancion sancion);

    @Mapping(source = "sancionado", target = "sancionado.idUsuario")
    @Mapping(source = "sancionador", target = "sancionador.idUsuario")
    Sancion toSancion(SancionDto sancionDto);
}
