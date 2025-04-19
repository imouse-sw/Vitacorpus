package org.imouse.vitacorpus.inicio;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.ui.SeleccionEjecutable;

public class Inicio
{
    public static void main(String[] args)
    {
        System.out.println("Ejecutando Vitacorpus...");
        SeleccionEjecutable.getInstance().run();
        System.out.println("¡Gracias por usar Vitacorpus!");
    }
}
