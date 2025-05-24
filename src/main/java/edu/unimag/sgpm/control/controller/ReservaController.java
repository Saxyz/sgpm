package edu.unimag.sgpm.control.controller;
import edu.unimag.sgpm.control.dto.ReservaDto;
import edu.unimag.sgpm.control.exceptions.EspacioNotFoundException;
import edu.unimag.sgpm.control.service.ReservaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
    @RequestMapping("/api/v1/reservas")
public class ReservaController {
    private final ReservaService reservaService;
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @GetMapping()
    public ResponseEntity<List<ReservaDto>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAllReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Integer id) {
        ReservaDto reserva = reservaService.findReservaById(id);
        if (reserva == null) {
            throw new EspacioNotFoundException("reserva no encontrado: " + id);
        }
        return ResponseEntity.ok(reserva);
    }

    @PostMapping()
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto resserva) {
        return createNewReserva(resserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Integer id, @RequestBody ReservaDto reserva) {
        try {
            ReservaDto updatedReserva = reservaService.updateReservaById(id, reserva);
            return ResponseEntity.ok(updatedReserva);
        } catch (RuntimeException e) {
            return createNewReserva(reserva);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Integer id) {
        reservaService.deleteReservaById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private static ResponseEntity<ReservaDto> createNewReserva(ReservaDto c) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id())
                .toUri();
        return ResponseEntity.created(location).body(c);
    }
}
