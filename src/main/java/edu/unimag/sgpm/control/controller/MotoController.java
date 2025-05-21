package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.MotoDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.MotoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/controller/v1/motos")
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
    public ResponseEntity<MotoDto> getMotoById(@PathVariable String id) {
        MotoDto espacio = motoService.findMotoById(id);
        if (espacio == null) {
            throw new EspacioNotFoundException("Espacio no encontrado: " + id);
        }
        return ResponseEntity.ok(espacio);
    }

    @PostMapping()
    public ResponseEntity<MotoDto> createMoto(@RequestBody MotoDto Espacio) {
        return createNewMoto(Espacio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDto> updateMoto(@PathVariable String id, @RequestBody MotoDto moto) {
        try {
            MotoDto updatedEspacio = motoService.updateMotoById(id, moto);
            return ResponseEntity.ok(updatedEspacio);
        } catch (RuntimeException e) {
            return createNewMoto(moto); // Si no existe, créalo
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoto(@PathVariable String id) {
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
}
