package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Restriccion;
import org.imouse.vitacorpus.model.Usuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_USUARIO_RESTRICCION")
@AttributeOverride(name = "id", column = @Column(name = "idRelUsuarioRestriccion"))
public class UsuarioRestriccion extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_USUARIO_idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CAT_ALIMENTOS_RESTRINGIDOS_idAlimento")
    private Restriccion restriccion;
}
