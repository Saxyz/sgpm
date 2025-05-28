package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.MotoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/motos")
public class MotoController {
    private final MotoService motoService;
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping()
    public ResponseEntity<List<MotoDto>> getAllMotos() {
        return ResponseEntity.ok(motoService.findAllMotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDto> getMotoById(@PathVariable Integer id) {
        MotoDto espacio = motoService.findMotoById(id);
        if (espacio == null) {
            throw new EspacioNotFoundException("Espacio no encontrado: " + id);
        }
        return ResponseEntity.ok(espacio);
    }

    @PostMapping()
    public ResponseEntity<MotoDto> createMoto(@ModelAttribute MotoDto moto, @RequestParam("imagen") MultipartFile imagen) throws IOException {
        String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get("C:\\Users\\Steven\\IdeaProjects\\sgpm-frontend\\src\\assets\\motoimg", nombreArchivo);
        Files.createDirectories(ruta.getParent());
        Files.write(ruta, imagen.getBytes());
        MotoDto motoDto = new MotoDto(moto.idMoto(), moto.placa(), moto.idUsuario(), moto.idParqueadero(), moto.modelo(), moto.descripcion(), ruta.toString());
        motoService.createMoto(motoDto);
        return createNewMoto(motoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDto> updateMoto(@PathVariable Integer id, @RequestBody MotoDto moto) {
        try {
            MotoDto updatedEspacio = motoService.updateMotoById(id, moto);
            return ResponseEntity.ok(updatedEspacio);
        } catch (RuntimeException e) {
            return createNewMoto(moto); // Si no existe, créalo
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoto(@PathVariable Integer id) {
        motoService.deleteMotoById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<MotoDto> createNewMoto(MotoDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idMoto())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<Resource> verImagenMoto(@PathVariable Integer id) throws IOException {
        Optional<MotoDto> motoOpt = Optional.of(motoService.findMotoById(id));

        MotoDto moto = motoOpt.get();
        Path ruta = Paths.get(Objects.requireNonNull(moto.ruta()));
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(recurso);
    }
}
