package edu.unimag.sgpm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EstadosDeEspacio")
@Builder
@Data
public class EstadoDeEspacio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstado;

    @Column(nullable = false)
    private String nombre;
}
