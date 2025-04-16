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
    @Column(name = "ejercicio", nullable = false)
    private String ejercicio;

    @Column(name = "duracion", nullable = false)
    private Time duracion;

    @Column(name = "instrucciones", nullable = false)
    private String instrucciones;

    @Column(name = "urlVideo")
    private String url;
}
