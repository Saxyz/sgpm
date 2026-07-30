package edu.unimag.sgpm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParqueadero", nullable = false)
    private Parqueadero parqueadero;

    @Column(nullable = false, length = 250)
    private String nombre;

    @Column(nullable = false, length = 250)
    private String apellido;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasenia;

    @Column(length = 250)
    private String codigo;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Moto> motos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;
}
