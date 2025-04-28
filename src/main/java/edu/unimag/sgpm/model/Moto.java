package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
