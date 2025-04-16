package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CAT_PREFERENCIA")
@AttributeOverride(name = "id", column=@Column(name = "idPreferencia"))
public class Preferencia extends Entidad
{
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
