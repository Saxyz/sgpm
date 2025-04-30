package edu.unimag.sgpm.model;

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
    private String idEspacio;

    @ManyToOne
    @JoinColumn(name = "idParqueadero", nullable = false)
    private Parqueadero parqueadero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoDeEspacio estado;
}