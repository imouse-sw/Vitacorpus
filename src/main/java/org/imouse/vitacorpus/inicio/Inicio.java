package org.imouse.vitacorpus.inicio;

import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.ui.SeleccionEjecutable;

public class Inicio
{
    public static void main(String[] args)
    {
        System.out.println("Ejecutando Vitacorpus...");
        HibernateUtil.getSessionFactory(); // Para evitar el lag de Hibernate cuando se hacen operaciones :P
        SeleccionEjecutable.getInstance().run();
        System.out.println("¡Gracias por usar Vitacorpus!");
    }
}
