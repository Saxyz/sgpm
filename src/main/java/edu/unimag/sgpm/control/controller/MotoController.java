package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.moto.MotoDTO;
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
    public ResponseEntity<List<MotoDTO>> getAllMotos() {
        return ResponseEntity.ok(motoService.findAllMotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> getMotoById(@PathVariable String id) {
        MotoDTO espacio = motoService.findMotoById(id);
        if (espacio == null) {
            throw new EspacioNotFoundException("Espacio no encontrado: " + id);
        }
        return ResponseEntity.ok(espacio);
    }

    @PostMapping()
    public ResponseEntity<MotoDTO> createMoto(@RequestBody MotoDTO Espacio) {
        return createNewMoto(Espacio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> updateMoto(@PathVariable String id, @RequestBody MotoDTO moto) {
        try {
            MotoDTO updatedEspacio = motoService.updateMotoById(id, moto);
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
    private static ResponseEntity<MotoDTO> createNewMoto(MotoDTO c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.idMoto())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
