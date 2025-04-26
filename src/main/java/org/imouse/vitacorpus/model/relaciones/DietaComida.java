package org.imouse.vitacorpus.model.relaciones;

import jakarta.persistence.*;
import lombok.*;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.Dieta;
import org.imouse.vitacorpus.model.Entidad;
import org.imouse.vitacorpus.model.Objetivo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REL_DIETA_COMIDA")
@AttributeOverride(name = "id", column = @Column(name = "idRelDietaComida"))
public class DietaComida extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_DIETA_idDieta")
    private Dieta dieta;

    @ManyToOne
    @JoinColumn(name = "TBL_COMIDA_idComida")
    private Comida comida;

    @Column(name = "cantidad", nullable = false)
    private double cantidad;
}
