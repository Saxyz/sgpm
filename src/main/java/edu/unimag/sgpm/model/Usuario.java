package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParqueadero", nullable = false)
    private Parqueadero parqueadero;

    @Column(nullable = false, length = 250)
    private String nombre;

    @Column(nullable = false, length = 250)
    private String apellido;
}
