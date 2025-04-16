package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CAT_OBJETIVOS")
@AttributeOverride(name = "id", column=@Column(name = "idObjetivos"))
public class Objetivo extends Entidad
{
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
