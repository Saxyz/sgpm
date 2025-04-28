package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Parqueaderos")
@Builder
@Data
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 250)
    private String nombre;

    @OneToMany(mappedBy = "parqueadero", fetch = FetchType.LAZY)
    private List<Espacio> espacios;
}
