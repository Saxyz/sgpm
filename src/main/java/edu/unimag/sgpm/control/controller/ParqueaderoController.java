package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.ParqueaderoDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.ParqueaderoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/parqueaderos")
public class ParqueaderoController {
    private final ParqueaderoService parqueaderoService;

    public ParqueaderoController(ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }

    @GetMapping()
    public ResponseEntity<List<ParqueaderoDto>> getAllParqueaderos() {
        return ResponseEntity.ok(parqueaderoService.findAllParqueaderos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParqueaderoDto> getParqueaderoById(@PathVariable Integer id) {
        ParqueaderoDto parqueadero = parqueaderoService.findParqueaderoById(id);
        if (parqueadero == null) {
            throw new EspacioNotFoundException("parqueadero no encontrado: " + id);
        }
        return ResponseEntity.ok(parqueadero);
    }

    @PostMapping()
    public ResponseEntity<ParqueaderoDto> createParqueadero(@RequestBody ParqueaderoDto parqueadero) throws IOException {
        String nombreArchivo = UUID.randomUUID() + "_" + parqueadero.imagen();
        Path ruta = Paths.get("C:\\Users\\Steven\\IdeaProjects\\sgpm-frontend\\src\\assets\\parqueaderoimg", nombreArchivo);
        Files.createDirectories(ruta.getParent());
        Files.write(ruta, parqueadero.imagen().getBytes());
        ParqueaderoDto parqueaderoDto = new ParqueaderoDto(parqueadero.id(), parqueadero.nombre(), parqueadero.imagen(), ruta.toString(), parqueadero.espacios());
        return createNewParqueadero(parqueaderoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParqueaderoDto> updatespacio(@PathVariable Integer id, @RequestBody ParqueaderoDto parqueadero) {
        try {
            ParqueaderoDto updatedParqueadero = parqueaderoService.updateParqueaderoById(id, parqueadero);
            return ResponseEntity.ok(updatedParqueadero);
        } catch (RuntimeException e) {
            ParqueaderoDto nuevoParqueadero = new ParqueaderoDto(id, parqueadero.nombre(),parqueadero.imagen(), "", parqueadero.espacios());
            return createNewParqueadero(nuevoParqueadero);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Integer id) {
        parqueaderoService.deleteParqueaderoById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ParqueaderoDto> createNewParqueadero(ParqueaderoDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<Resource> verImagenParqueadero(@PathVariable Integer id) throws IOException {
        Optional<ParqueaderoDto> parqueaderoOpt = Optional.of(parqueaderoService.findParqueaderoById(id));

        ParqueaderoDto parqueadero = parqueaderoOpt.get();
        Path ruta = Paths.get(parqueadero.ruta());
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(recurso);
    }
}
