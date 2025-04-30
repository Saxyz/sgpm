package edu.unimag.sgpm.model;

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

    @OneToMany(mappedBy = "parqueadero", fetch = FetchType.LAZY)
    private List<Espacio> espacios;
}
