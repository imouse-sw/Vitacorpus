package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Preferencia;
import org.imouse.vitacorpus.model.Usuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_USUARIO_PREF")
@AttributeOverride(name = "id", column = @Column(name = "idRelUsuarioPref"))
public class UsuarioPreferencia extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_USUARIO_idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CAT_PREFERENCIA_idPreferencia")
    private Preferencia preferencia;
}
