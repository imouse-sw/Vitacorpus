package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_EJERCICIO")
@AttributeOverride(name = "id", column=@Column(name = "idEjercicio"))
public class Ejercicio extends Entidad
{
    @Column(name = "ejercicioLunes", nullable = false)
    private String ejercicioLunes;

    @Column(name = "ejercicioMartes", nullable = false)
    private String ejercicioMartes;

    @Column(name = "ejercicioMiercoles", nullable = false)
    private String ejercicioMiercoles;

    @Column(name = "ejercicioJueves", nullable = false)
    private String ejercicioJueves;

    @Column(name = "ejercicioViernes", nullable = false)
    private String ejercicioViernes;

    @Column(name = "ejercicioSabado", nullable = false)
    private String ejercicioSabado;

    @Column(name = "ejercicioDomingo", nullable = false)
    private String ejercicioDomingo;
}