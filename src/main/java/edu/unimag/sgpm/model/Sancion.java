package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Sanciones")
@Builder
@Data
public class Sancion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSancion;

    @ManyToOne
    @JoinColumn(name = "idSancionado", nullable = false)
    private Usuario sancionado;

    @ManyToOne
    @JoinColumn(name = "idSancionador", nullable = false)
    private Administrador sancionador;

    @Column(nullable = false)
    private LocalDate fecha;

    private String descripcion;
}
