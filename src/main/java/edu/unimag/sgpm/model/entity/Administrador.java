package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Administradores")
@PrimaryKeyJoinColumn(name = "idAdministrador")
public class Administrador extends Usuario{
}
