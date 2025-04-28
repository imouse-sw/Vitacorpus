package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CAT_ALIMENTOS_RESTRINGIDOS")
@AttributeOverride(name = "id", column=@Column(name = "idAlimento"))
public class Restriccion extends Entidad
{
    @Column(name = "alimento", nullable = false)
    private String alimento;

    @Column(name = "sustituto1", nullable = false)
    private String sustituto1;

    @Column(name = "sustituto2")
    private String sustituto2;
}
