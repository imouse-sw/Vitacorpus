package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_RUTINA")
@AttributeOverride(name = "id", column=@Column(name = "idRutina"))
public class Rutina extends Entidad
{
    @Column(name = "rutina", nullable = false)
    private String rutina;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precauciones")
    private String precauciones;

    @Column(name = "gradoDificultad", nullable = false)
    private int dificultad;

    @ManyToOne
    @JoinColumn(name = "CAT_OBJETIVO_idObjetivos")
    private Objetivo objetivo;
}
