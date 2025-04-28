package edu.unimag.sgpm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vigilantes")
@Builder
@PrimaryKeyJoinColumn(name = "idVigilante")
public class Vigilante extends Usuario{

}
