package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Profesores")
@PrimaryKeyJoinColumn(name = "idProfesor")
public class Profesor extends Usuario {

}
