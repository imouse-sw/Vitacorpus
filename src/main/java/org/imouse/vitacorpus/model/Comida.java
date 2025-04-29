package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_COMIDA")
@AttributeOverride(name = "id", column=@Column(name = "idComida"))
public class Comida extends Entidad
{
    @Column(name = "comidasLunes", nullable = false)
    private String comidasLunes;

    @Column(name = "comidasMartes", nullable = false)
    private String comidasMartes;

    @Column(name = "comidasMiercoles", nullable = false)
    private String comidasMiercoles;

    @Column(name = "comidasJueves", nullable = false)
    private String comidasJueves;

    @Column(name = "comidasViernes", nullable = false)
    private String comidasViernes;

    @Column(name = "comidasSabado", nullable = false)
    private String comidasSabado;

    @Column(name = "comidasDomingo", nullable = false)
    private String comidasDomingo;
}
