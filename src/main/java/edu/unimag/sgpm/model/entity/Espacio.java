package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Espacios")
@Builder
@Data
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspacio;

    @ManyToOne
    @JoinColumn(name = "idParqueadero", nullable = false)
    private Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private EstadoDeEspacio estado;

    @Column(nullable = false)
    private String numero;
}