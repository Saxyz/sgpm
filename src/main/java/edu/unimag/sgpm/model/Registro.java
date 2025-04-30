package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Registros")
@Builder
@Data
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRegistro;

    @ManyToOne
    @JoinColumn(name = "idEspacio", nullable = false)
    private Espacio espacio;

    @ManyToOne
    @JoinColumn(name = "idMoto", nullable = false)
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "idVigilante", nullable = false)
    private Vigilante vigilante;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaEntrada;

    private LocalTime horaSalida;
}
