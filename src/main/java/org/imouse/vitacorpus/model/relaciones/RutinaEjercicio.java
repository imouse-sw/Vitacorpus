package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Ejercicio;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Objetivo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_RUTINA_Ej")
@AttributeOverride(name = "id", column = @Column(name = "idRelRutinaEjercicio"))
public class RutinaEjercicio extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_RUTINA_idRutina")
    private Rutina rutina;

    @ManyToOne
    @JoinColumn(name = "TBL_EJERCICIO_idEjercicio")
    private Ejercicio ejercicio;
}
