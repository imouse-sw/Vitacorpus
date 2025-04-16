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
@Table(name = "TBL_CALCULADORA")
@AttributeOverride(name = "id", column=@Column(name = "idCalculadora"))
public class Calculadora extends Entidad
{
    @Column(name = "horaDespertar", nullable = false)
    private Time horaDespertar;

    @Column(name = "horaDormir", nullable = false)
    private Time horaDormir;
}
