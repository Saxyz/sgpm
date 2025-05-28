package edu.unimag.sgpm.control.controller;

import edu.unimag.sgpm.control.dto.EspacioDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.EspacioService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
    @RestController
    @RequestMapping("/api/v1/espacios")
    public class EspacioController {
        private final EspacioService espacioService;
        public EspacioController(EspacioService espacioService) {
            this.espacioService = espacioService;
        }

        @GetMapping()
        public ResponseEntity<List<EspacioDto>> getAllEspacios() {
            return ResponseEntity.ok(espacioService.findAllEspacios());
        }

        @GetMapping("/{id}")
        public ResponseEntity<EspacioDto> getEspacioById(@PathVariable Integer id) {
            EspacioDto espacio = espacioService.findEspacioById(id);
            if (espacio == null) {
                throw new EspacioNotFoundException("Espacio no encontrado: " + id);
            }
            return ResponseEntity.ok(espacio);
        }

        @PostMapping()
        public ResponseEntity<EspacioDto> createEspacio(@RequestBody EspacioDto Espacio) {
            espacioService.createEspacio(Espacio);
            return createNewEspacio(Espacio);
        }

        @PutMapping("/{id}")
        public ResponseEntity<EspacioDto> updateEspacio(@PathVariable Integer id, @RequestBody EspacioDto espacio) {
            try {
                EspacioDto updatedEspacio = espacioService.updateEspacio(id, espacio);
                return ResponseEntity.ok(updatedEspacio);
            } catch (RuntimeException e) {
                return createNewEspacio(espacio); // Si no existe, créalo
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEspacio(@PathVariable Integer id) {
            espacioService.deleteEspacioById(id);
            return ResponseEntity.noContent().build();
        }

        @NotNull
        private static ResponseEntity<EspacioDto> createNewEspacio(EspacioDto c) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(c.id())
                    .toUri();
            return ResponseEntity.created(location).body(c);
        }
}
