package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_DIETA")
@AttributeOverride(name = "id", column=@Column(name = "idDieta"))
public class Dieta extends Entidad
{
    @Column(name = "dieta", nullable = false)
    private String dieta;

    @Column(name = "caloriasTotales", nullable = false)
    private double caloriasTotales;

    @Column(name = "restricMonetarias")
    private String restricciones;

    @ManyToOne
    @JoinColumn(name = "CAT_OBJETIVO_idObjetivos")
    private Objetivo objetivo;
}
