package edu.unimag.sgpm.control.mapper;

import edu.unimag.sgpm.control.dto.SancionDto;
import edu.unimag.sgpm.model.entity.Sancion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SancionMapper {

    @Mapping(source = "sancionado.idUsuario", target = "sancionado")
    @Mapping(source = "sancionador.idUsuario", target = "sancionador")
    @Mapping(source = "idSancion", target = "id")
    SancionDto toSancionDto(Sancion sancion);

    @Mapping(source = "sancionado", target = "sancionado.idUsuario")
    @Mapping(source = "sancionador", target = "sancionador.idUsuario")
    @Mapping(source = "id", target = "idSancion")
    Sancion toSancion(SancionDto sancionDto);

    List<SancionDto> toSancionDtos(List<Sancion> sanciones);

    List<Sancion> toSancions(List<SancionDto> sancionDtos);
}
