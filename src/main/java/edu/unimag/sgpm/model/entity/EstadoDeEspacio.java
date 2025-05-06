package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "EstadosDeEspacio")
public class EstadoDeEspacio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstado;

    @Column(nullable = false)
    private String nombre;
}
