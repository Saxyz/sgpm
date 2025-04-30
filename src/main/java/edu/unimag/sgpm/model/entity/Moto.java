package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Motos")
@Builder
@Data
public class Moto {

    @Id
    private String idMoto;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idParqueadero", nullable = false)
    private Parqueadero parqueadero;

    @Column(nullable = false)
    private String modelo;

    private String descripcion;

}
