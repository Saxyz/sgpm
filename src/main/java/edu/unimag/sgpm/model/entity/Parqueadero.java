package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Parqueaderos")
@Builder
@Data
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idParqueadero;

    @Column(nullable = false, length = 250)
    private String nombre;

    @OneToMany(mappedBy = "parqueadero", fetch = FetchType.EAGER)
    private List<Espacio> espacios;

    @Column
    private String imagen;
}
