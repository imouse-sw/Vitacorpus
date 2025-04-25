package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_USUARIO")
@AttributeOverride(name = "id", column = @Column(name = "idUsuario"))
public class Usuario extends Entidad
{
    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String password;

    @Column(name = "fechaRegistro", nullable = false, updatable = false, insertable = false)
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "CAT_OBJETIVO_idObjetivos")
    private Objetivo objetivo;
}
