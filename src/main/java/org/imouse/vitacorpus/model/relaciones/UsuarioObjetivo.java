package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Usuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_USUARIO_OBJ")
@AttributeOverride(name="id", column = @Column(name = "idRelUsuarioObjetivo"))
public class UsuarioObjetivo extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_USUARIO_idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CAT_OBJETIVO_idObjetivos", nullable = false)
    private Objetivo objetivo;
}
