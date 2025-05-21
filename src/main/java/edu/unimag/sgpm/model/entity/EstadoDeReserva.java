package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "EstadosDeReserva")
@Builder
@Data
public class EstadoDeReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstado;

    @Column(nullable = false)
    private String nombre;
}
