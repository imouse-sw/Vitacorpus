package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Restriccion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_COMIDA_RESTRICCION")
@AttributeOverride(name = "id", column = @Column(name = "idRelComidaRestriccion"))
public class ComidaRestriccion extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_COMIDA_idComida")
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "CAT_ALIMENTOS_RESTRINGIDOS_idAlimento")
    private Restriccion restriccion;
}
