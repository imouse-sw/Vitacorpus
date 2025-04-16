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
    @Column(name = "comida", nullable = false)
    private String comida;

    @Column(name = "numCalorias", nullable = false)
    private double calorias;

    @Column(name = "restricAlimenticias")
    private String restricciones;
}
