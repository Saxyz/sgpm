package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reservas")
@Builder
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private EstadoDeReserva estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idEspacio", nullable = false)
    private Espacio espacio;

    @Column(nullable = false)
    private LocalDateTime fecha;
}
