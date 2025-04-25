package org.imouse.vitacorpus.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_REGISTRO_DATOS")
@AttributeOverride(name = "id", column=@Column(name = "idRegistro"))
public class RegistroDatos extends Entidad
{
    @ManyToOne
    @JoinColumn(name = "TBL_USUARIO_idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "peso", nullable = false)
    private double peso;

    @Column(name = "estatura", nullable = false)
    private double estatura;

    @Column(name = "fechaActualizacion", insertable = false, updatable = false, nullable = false)
    private Timestamp fechaActualizacion;

    @Column(name = "imc", nullable = false)
    private double imc;

    @PrePersist
    @PreUpdate
    private void calcularIMC()
    {
        if(estatura>0)
        {
            this.imc = peso/(estatura*estatura);
        }
        else
        {
            this.imc = 0;
        }
    }
}
